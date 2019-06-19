package com.elvin.aaos.core.validation;

import com.elvin.aaos.core.model.dto.ExamDto;
import com.elvin.aaos.core.model.entity.Exam;
import com.elvin.aaos.core.model.repository.ExamRepository;
import com.elvin.aaos.web.error.ExamError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExamValidation {

    private ExamError examError = new ExamError();
    private boolean valid = true;
    private final DateValidation dateValidation;
    private final ExamRepository examRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ExamValidation(
            @Autowired DateValidation dateValidation,
            @Autowired ExamRepository examRepository
    ) {
        this.dateValidation = dateValidation;
        this.examRepository = examRepository;
    }

    public ExamError saveValidation(ExamDto examDto) {

        valid = true;

        examError.setName(checkName(examDto.getName()));
        examError.setStart(checkStartDate(examDto.getStart()));
        examError.setEnd(checkEndDate(examDto.getStart() ,examDto.getEnd()));
        examError.setStartTime(checkTime(examDto.getStrStartTime(), "start time"));
        examError.setEndTime(checkTime(examDto.getStrEndTime(), "end time"));

        examError.setValid(valid);
        return examError;
    }

    public ExamError updateValidation(ExamDto examDto) {

        Exam exam = examRepository.findExamById(examDto.getId());
        valid = true;

        if (StringUtils.isBlank(examDto.getName()) || !examDto.getName().equals(examDto.getName())) {
            examError.setName(checkName(examDto.getName()));
        }
        examError.setStart(checkStartDate(examDto.getStart()));
        examError.setEnd(checkEndDate(examDto.getStart() ,examDto.getEnd()));
        examError.setStartTime(checkTime(examDto.getStrStartTime(), "start time"));
        examError.setEndTime(checkTime(examDto.getStrEndTime(), "end time"));

        examError.setValid(valid);
        return examError;

    }

    private String checkName(String name) {
        if (StringUtils.isNotBlank(name)){
            if (examRepository.findExamByName(name) != null) {
                logger.debug("EXAM NAME ALREADY EXISTS");
                valid = false;
                return "exam name already exists";
            }
        } else {
            logger.debug("EXAM NAME CANNOT BE NULL OR EMPTY");
            valid = false;
            return "exam name cannot be null or empty";
        }

        return "";
    }

    private String checkTime(String time, String target) {
        if (StringUtils.isNotBlank(time)){
            if (!dateValidation.isValidTime(time)) {
                logger.debug("INVALID " + target.toUpperCase());
                valid = false;
                return "invalid " + target;
            }
        } else {
            logger.debug(target.toUpperCase() + " CANNOT BE NULL OR EMPTY");
            valid = false;
            return target + " cannot be null or empty";
        }

        return "";
    }

    private String checkStartDate(Date startDate) {
        if (!dateValidation.isPastDate(startDate)) {
            valid = false;
            logger.debug("exam start date must not be future date");
            return "exam start date must not be future date";
        }
        return "";
    }

    private String checkEndDate(Date startDate, Date endDate) {
        if (endDate.before(startDate)) {
            valid = false;
            logger.debug("exam end date must not be before start date");
            return "exam end date must not be before start date";
        }

        return "";
    }

}
