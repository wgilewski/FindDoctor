package wg.app.model.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyException extends RuntimeException
{

    private ExceptionInfo exceptionInfo;

    public MyException(String exceptionMessage)
    {
        this.exceptionInfo = new ExceptionInfo(exceptionMessage);
    }
}
