package br.com.pvprojects.loja;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LojaApplicationTests {


    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    @Test
    public void contextLoads() {
        String a = uuidGenerator();
        System.out.println("A: "+ a);
        UUID b = UUID.randomUUID();
        System.out.println("B: " + b);
        System.out.println("C: " + b.toString());
    }

    public String uuidGenerator() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID + "-" + dateFormatter.format(new Date());
    }


}
