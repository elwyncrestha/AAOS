package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.*;
import com.elvin.aaos.core.model.enums.MessageType;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.*;
import com.elvin.aaos.core.validation.BatchValidation;
import com.elvin.aaos.web.error.BatchError;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import com.elvin.aaos.web.utility.auth.AuthorizationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/batch")
public class BatchController {

    private final BatchService batchService;
    private final BatchValidation batchValidation;
    private final AuthorizationUtil authorizationUtil;
    private final StudentProfileService studentProfileService;
    private final RoomScheduleService roomScheduleService;
    private final CourseService courseService;
    private final ExamService examService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public BatchController(
            @Autowired BatchService batchService,
            @Autowired BatchValidation batchValidation,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired StudentProfileService studentProfileService,
            @Autowired RoomScheduleService roomScheduleService,
            @Autowired CourseService courseService,
            @Autowired ExamService examService
            ) {
        this.batchService = batchService;
        this.batchValidation = batchValidation;
        this.authorizationUtil = authorizationUtil;
        this.studentProfileService = studentProfileService;
        this.roomScheduleService = roomScheduleService;
        this.courseService = courseService;
        this.examService = examService;
    }

    private void batchCards(ModelMap modelMap) {
        modelMap.put(StringConstants.BATCH_COUNT, batchService.countTotalBatch());
    }

    @GetMapping(value = "/add")
    public String addBatchForm(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        batchCards(modelMap);
        logger.info("GET:/batch/add");
        return "batch/add";
    }

    @PostMapping(value = "/add")
    public String addBatch(@ModelAttribute BatchDto batchDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        BatchError batchError = batchValidation.saveValidation(batchDto);
        if (!batchError.isValid()) {
            logger.debug("Batch is not valid");
            modelMap.put(StringConstants.ERROR, batchError);
            modelMap.put(StringConstants.BATCH, batchDto);
            return "batch/add";
        }

        batchService.save(batchDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Batch added successfully");
        logger.info("Batch added successfully");

        return "redirect:/batch/display";
    }

    @GetMapping(value = "/display")
    public String displayBatch(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        batchCards(modelMap);
        List<BatchDto> batchDtoList = batchService.list();
        modelMap.put(StringConstants.BATCH_LIST, batchDtoList);
        logger.info("GET:/batch/display");

        return "batch/display";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBatch(@PathVariable("id") long batchId, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        BatchDto batchDto = batchService.getBatch(batchId);
        if (batchDto == null) {
            logger.debug("Batch Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Batch Not Found");
        }

        // remove associated student first
        if (studentProfileService.hasAssociatedBatch(batchId)) {
            logger.debug("Cannot delete batch having associated students");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Remove associated students first");
            return "redirect:/batch/display";
        }
        // remove associated room schedule first
        else if (roomScheduleService.hasAssociatedBatch(batchId)) {
            logger.debug("Cannot delete batch having associated room schedules");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Remove associated room schedules first");
            return "redirect:/batch/display";
        }

        batchService.delete(batchId, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Batch Deleted Successfully");
        logger.info("Batch Deleted Successfully");
        return "redirect:/batch/display";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEditForm(@PathVariable("id") long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        BatchDto batchDto = batchService.getBatch(id);
        if (batchDto == null) {
            logger.debug("Batch Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Batch Not Found");
        }

        modelMap.put(StringConstants.BATCH, batchDto);
        logger.info("GET:/batch/edit");
        return "batch/edit";
    }

    @PostMapping(value = "/edit")
    public String editBatch(@ModelAttribute BatchDto batchDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        if (batchDto == null || batchDto.getId() < 0) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Bad Request");
            logger.debug("Bad Request");
            return "redirect:/batch/display";
        }

        if (batchService.getBatch(batchDto.getId()) == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Batch Not Found");
            logger.debug("Batch Not Found");
            return "redirect:/batch/display";
        }

        BatchError batchError = batchValidation.updateValidation(batchDto);
        if (!batchError.isValid()) {
            logger.debug("Batch is not valid");
            modelMap.put(StringConstants.ERROR, batchError);
            modelMap.put(StringConstants.BATCH, batchDto);
            return "batch/edit";
        }

        batchService.update(batchDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Batch edited successfully");
        logger.info("Batch edited successfully");

        return "redirect:/batch/display";
    }

    @GetMapping(value = "/assign")
    public String getAssign(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        modelMap.put(StringConstants.STUDENT_PROFILE_LIST, studentProfileService.list());
        modelMap.put(StringConstants.BATCH_LIST, batchService.list());
        modelMap.put(StringConstants.ASSIGNED_BATCH_COUNT, studentProfileService.countBatchAssigned());
        modelMap.put(StringConstants.UNASSIGNED_BATCH_COUNT, studentProfileService.countBatchUnassigned());
        return "student/assignBatch";
    }

    @GetMapping(value = "/course/enroll")
    public String getEnroll(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        modelMap.put(StringConstants.BATCH_LIST, batchService.list());
        return "batch/enrollCourses";
    }

    @PostMapping(value = "/{batchId}/enroll")
    @ResponseBody
    public ResponseEntity<ResponseDto> getCoursesForEnroll(@PathVariable("batchId") long batchId) {
        ResponseDto responseDto = new ResponseDto();
        if (AuthenticationUtil.currentUserIsNull()) {
            responseDto.setMessage("Unauthenticated User");
            responseDto.setStatus("401");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        } else if (!AuthenticationUtil.isAdmin()) {
            responseDto.setMessage("You have no permission to access the URL");
            responseDto.setStatus("403");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }

        List<CourseDto> allCourses = courseService.list();
        BatchCourseDto batchCourseDto = batchService.batchWithCourses(batchId);

        responseDto.setObject(new HashMap<String, Object>(){{
            put("AllCourses", allCourses);
            put("EnrolledCourses", batchCourseDto.getCourses());
        }});
        responseDto.setMessage("Returned course list for batch " + batchCourseDto.getName());
        responseDto.setStatus("200");
        responseDto.setMessageType(MessageType.SUCCESS);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping(value = "/course/enroll")
    public String enrollCourses(@RequestParam("batchId") long batchId, @RequestParam("enrolledCourses") String[] enrolledCoursesId, RedirectAttributes redirectAttributes) {
        BatchCourseDto batchCourseDto = new BatchCourseDto();
        batchCourseDto.setId(batchId);
        Set<CourseDto> courseDtoSet = new HashSet<>();
        for (String courseId: enrolledCoursesId) {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(Long.parseLong(courseId));
            courseDtoSet.add(courseDto);
        }
        batchCourseDto.setCourses(courseDtoSet);


        batchService.enrollCourses(batchCourseDto);
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Course Enrollment Successful");
        logger.info("Course Enrollment Successful");
        return "redirect:/batch/course/enroll";
    }

    @PostMapping(value = "/{batchId}/exam")
    @ResponseBody
    public ResponseEntity<ResponseDto> getExamsForAssigning(@PathVariable("batchId") long batchId) {
        ResponseDto responseDto = new ResponseDto();
        if (AuthenticationUtil.currentUserIsNull()) {
            responseDto.setMessage("Unauthenticated User");
            responseDto.setMessageType(MessageType.ERROR);
            responseDto.setStatus("401");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ACADEMIC_STAFF)) {
            responseDto.setMessage("You have no permission to access the URL");
            responseDto.setMessageType(MessageType.ERROR);
            responseDto.setStatus("403");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }

        List<ExamModuleDto> allExams = examService.list();
        BatchExamDto batchExamDto = batchService.batchWithExams(batchId);

        responseDto.setObject(new HashMap<String, Object>() {{
            put("AllExams", allExams);
            put("AssignedExams", batchExamDto.getExams());
        }});
        responseDto.setMessage("Returned exam list for batch " + batchExamDto.getName());
        responseDto.setStatus("200");
        responseDto.setMessageType(MessageType.SUCCESS);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping(value = "/exam/assign")
    public String assignExams(@RequestParam("batchId") long batchId, @RequestParam("assignedExams") String[] assignedExamsId, RedirectAttributes redirectAttributes) {
        BatchExamDto batchExamDto = new BatchExamDto();
        batchExamDto.setId(batchId);
        Set<ExamDto> examDtoSet = new HashSet<>();
        for (String examId : assignedExamsId) {
            ExamDto examDto = new ExamDto();
            examDto.setId(Long.parseLong(examId));
            examDtoSet.add(examDto);
        }
        batchExamDto.setExams(examDtoSet);


        batchService.assignExams(batchExamDto);
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Exam Assignment Successful");
        logger.info("Exam Assignment Successful");
        return "redirect:/exam/assign";
    }

}
