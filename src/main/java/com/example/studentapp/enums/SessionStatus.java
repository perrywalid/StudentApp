package com.example.studentapp.enums;

public enum SessionStatus {

    //Student sends request
    PENDING,
    //Student pays for session
    ACCEPTED,
    //Teacher rejects initial request
    REJECTED,
    //Teacher accepts initial request, waiting on student to pay
    PENDINGPAYMENT,
    HOSTEDTEACHER,
    COMPLETEDSTUDENT,
}