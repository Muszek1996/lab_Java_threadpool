import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class main {
    public static void main(String[] args) {
        double xp,xk,dx,width;
        xp = 0;
        xk = Math.PI;
        dx = 0.0001;
        width = (xk-xp)/100;



           double result = 0;
        for(int i = 0; i<100;i++){
            double xpL = i*width;
            double xkL = i*width+width;
            Calka_callable calka_callable = new Calka_callable(xpL,xkL,dx);
            try {
                result += calka_callable.compute();
            }catch (Exception e){
                e.printStackTrace();
            }
            }
        System.out.println("Calka sekwencyjnie:"+result);




        int threadNum = 8;
    ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        List<Future<Double>> futures = new ArrayList<>();


        System.out.println("xp:"+xp+" xk:"+xk);

        ;


        for(int i = 0; i<100;i++){
            double xpL = i*width;
            double xkL = i*width+width;
           futures.add(executorService.submit(new Calka_callable(xpL,xkL,dx)));
        }
        result = 0;

        for(Future<Double> f : futures){
            try {
                result += f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }



        System.out.println("Result = " + result);




    }
}
