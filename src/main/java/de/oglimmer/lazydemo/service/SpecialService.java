package de.oglimmer.lazydemo.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpecialService implements ApplicationContextAware, Runnable {

    static ApplicationContext applicationContext;

    public void run() {
        try {
            Thread.sleep(15000);
            while (true) {
                try {
                    if (applicationContext != null) {
                        PersonService personService = (PersonService) applicationContext.getBean("personService");
                        personService.getPersons();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Thread.sleep(500000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpecialService.applicationContext = applicationContext;
        new Thread(this).start();
    }

}
