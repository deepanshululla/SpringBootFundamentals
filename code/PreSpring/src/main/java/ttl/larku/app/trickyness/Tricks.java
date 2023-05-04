package ttl.larku.app.trickyness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author whynot
 */
interface Trick {
    public void doTrick();
}

@Component
//@Primary
@Qualifier("us-west")
//@Profile("us-west")
class Trick1 implements Trick
{
    @Override
    public void doTrick() {
        System.out.println("Handstand");
    }
}

@Component
@Qualifier("us-east")
//@Profile("us-east")
@Order(2)
class Trick2 implements Trick
{
    @Override
    public void doTrick() {
        System.out.println("Cartwheel");
    }
}

@Component
//@Profile("us-mountain")
@Qualifier("us-east")
@Order(1)
class Trick3 implements Trick
{
    @Override
    public void doTrick() {
        System.out.println("Somersault");
    }
}

@Component
class Circus
{
    @Autowired
    private Trick trick2;

    @Autowired
    @Qualifier("us-west")
    public List<Trick> westTricks;

    @Autowired
    @Qualifier("us-east")
    private List<Trick> tricks;

    public void startShow() {
        trick2.doTrick();
//        tricks.forEach(Trick::doTrick);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("us-east");
        context.scan("ttl.larku.app.trickyness");
        context.refresh();

        Circus circus = context.getBean("circus", Circus.class);

        circus.startShow();
    }
}
