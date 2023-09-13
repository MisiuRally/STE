package STE.API.controller;

import STE.domain.exeption.NotFoundException;
import STE.domain.exeption.ProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleException(Exception ex) {
        String message = "Nie znaleziono – serwer nie odnalazł zasobu według podanego URL ani niczego co by wskazywało na istnienie takiego zasobu w przeszłości ";
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoResourceFound(NotFoundException ex) {
        String message = "Nie znaleziono – serwer nie odnalazł zasobu według podanego URL ani niczego co by wskazywało na istnienie takiego zasobu w przeszłości ";
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(ProcessingException ex) {
        String message = "Wewnętrzny błąd serwera – serwer napotkał niespodziewane trudności, które uniemożliwiły zrealizowanie żądania ";
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(BindException ex) {
        String message = "Nieprawidłowe zapytanie – żądanie nie może być obsłużone przez serwer z powodu nieprawidłowości postrzeganej jako błąd użytkownika (np. błędna składnia zapytania) ";
        log.error(message, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }

    @ExceptionHandler({ AuthenticationException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView securityException(){
        String message ="Brak uprawnień";
        log.error(message);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", message);
        return modelView;
    }





}
