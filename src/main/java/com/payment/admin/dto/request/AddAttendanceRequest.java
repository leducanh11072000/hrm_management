package com.payment.admin.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddAttendanceRequest {
    private String dateAttendance;
    private String timeIn;
    private String timeOut;
}
