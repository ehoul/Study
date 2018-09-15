package ehoul.study;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import ehoul.study.pojo.EsBodyMeasurements;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

       // go !
        BMDCheckResult result = new BMDCheckResult();
        EsBodyMeasurements bmd = new EsBodyMeasurements();
        bmd.setMeasurementRecordId(1);
        kSession.insert(bmd);//插入
        kSession.insert(result);//插入
        kSession.fireAllRules();//执行规则
        kSession.dispose();
        System.out.println(result.getMessage());
    }
}
