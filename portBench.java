import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.security.Key;
import java.util.*;

/**
 * Created by TITASH MANDAL on 8/31/2017.
 */
public class portBench {
    public static void main(String args[]){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s="PORT:AXN,10,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20";
        int index=s.indexOf("|");
        //split the Bench and Portfolio first and make two strings
        String newPortfolio=" ",newBench=" ";
        if (index != -1){
            newPortfolio= (s.substring(0 , index)).replace("PORT:","");
            newBench=s.substring(index+1,s.length()).replace("BENCH:","");
        }


        String[] words=newPortfolio.split(";");
        String[] benches=newBench.split(";");
        Map<String,String> Portfolio_Items=new TreeMap<String,String>();
        Map<String,String> Bench_Items=new TreeMap<String,String>();



        for(int i=0;i<words.length;i++){
            String temp=words[i];
            String[] individuals=temp.split(",");
            String numbers=individuals[1]+","+individuals[2];
            Portfolio_Items.put(individuals[0],numbers);
        }
        for(int i=0;i<benches.length;i++){
            String temp=benches[i];
            String[] individuals=temp.split(",");
            String numbers=individuals[1]+","+individuals[2];
            Bench_Items.put(individuals[0],numbers);
        }

        Set set = Portfolio_Items.entrySet();
        Iterator iterator = set.iterator();
        Set set2=Bench_Items.entrySet();
        Iterator iterator1=set2.iterator();


        int PortAQty=0,PortAPrice=0,PortBQty=0,PortBPrice=0,PortCQty=0,PortCPrice=0;
        int BenchAQty=0,BenchAPrice=0,BenchBQty=0,BenchBPrice=0,BenchCQty=0,BenchCPrice=0,BenchXQty=0,BenchXPrice=0,BenchDQty=0,BenchDPrice=0;

        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            String value=(String)mentry.getValue();
            String[] individual_Value=value.split(",");
            String KeyValue=(String)mentry.getKey();
            if(KeyValue.equals("AXN")){
                PortAQty=Integer.parseInt(individual_Value[0]);
                PortAPrice=Integer.parseInt(individual_Value[1]);
            }
            else if(KeyValue.equals("BGT")){
                PortBQty=Integer.parseInt(individual_Value[0]);
                PortBPrice=Integer.parseInt(individual_Value[1]);
            }
            else{
                PortCQty=Integer.parseInt(individual_Value[0]);
                PortCPrice=Integer.parseInt(individual_Value[1]);
            }


        }
        Set set21=Bench_Items.entrySet();
        Iterator iterator12=set21.iterator();

        while(iterator12.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator12.next();
            String value=(String)mentry.getValue();
            String[] individual_Value=value.split(",");
            String KeyValue=(String)mentry.getKey();

            if(KeyValue.equals("AXN")){
                BenchAQty=Integer.parseInt(individual_Value[0]);
                BenchAPrice=Integer.parseInt(individual_Value[1]);

            }
            else if(KeyValue.equals("BGT")){
                BenchBQty=Integer.parseInt(individual_Value[0]);
                BenchBPrice=Integer.parseInt(individual_Value[1]);

            }
            else if(KeyValue.equals("CXZ")){
                BenchCQty=Integer.parseInt(individual_Value[0]);
                BenchCPrice=Integer.parseInt(individual_Value[1]);

            }

            else if(KeyValue.equals("DFG")){
                BenchDQty=Integer.parseInt(individual_Value[0]);
                BenchDPrice=Integer.parseInt(individual_Value[1]);

            }
            else{
                BenchXPrice=Integer.parseInt(individual_Value[0]);
                BenchXQty=Integer.parseInt(individual_Value[1]);

            }
        }
        double Nav_Port_Portfolio=(PortAPrice*PortAQty)+(PortBPrice*PortBQty)+(PortCPrice*PortCQty);
        double Percent_Nav_A=(PortAPrice*PortAQty*100)/Nav_Port_Portfolio;
        double Percent_Nav_B=(PortBPrice*PortBQty*100)/Nav_Port_Portfolio;
        double Percent_Nav_C=(PortCPrice*PortCQty*100)/Nav_Port_Portfolio;
        double Percent_Nav_D=0;
        double Percent_Nav_X=0;
        double Nav_Port_Bench=(BenchAPrice*BenchAQty)+(BenchBPrice*BenchBQty)+(BenchCPrice*BenchCQty)+(BenchDPrice*BenchDQty)+(BenchXPrice*BenchXQty);
        double Percent_Bench_Nav_A=(BenchAPrice*BenchAQty*100)/Nav_Port_Bench;
        double Percent_Bench_Nav_B=(BenchBPrice*BenchBQty*100)/Nav_Port_Bench;
        double Percent_Bench_Nav_D=(BenchDPrice*BenchDQty*100)/Nav_Port_Bench;
        double Percent_Bench_Nav_X=(BenchXPrice*BenchXQty*100)/Nav_Port_Bench;

        Set set212=Bench_Items.entrySet();
        Iterator iterator121=set212.iterator();

        while(iterator121.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator121.next();
            String KeyValue=(String)mentry.getKey();
            if(KeyValue.equals("AXN")){
                double difference=Percent_Nav_A-Percent_Bench_Nav_A;
                Bench_Items.put(KeyValue,Double.toString(difference));
                System.out.print(mentry.getKey()+":"+mentry.getValue()+",");
            }
            else if(KeyValue.equals("BGT")){
                double difference=Percent_Nav_B-Percent_Bench_Nav_B;
                Bench_Items.put(KeyValue,Double.toString(difference));
                System.out.print(mentry.getKey()+":"+mentry.getValue()+",");
            }
            else if(KeyValue.equals("DFG")){
                double difference=Percent_Nav_D-Percent_Bench_Nav_D;
                Bench_Items.put(KeyValue,Double.toString(difference));
                System.out.print(mentry.getKey()+" "+mentry.getValue()+",");
            }
            else{
                double difference=Percent_Nav_X-Percent_Bench_Nav_X;
                Bench_Items.put(KeyValue,Double.toString(difference));
                System.out.print(mentry.getKey()+" "+mentry.getValue()+",");
            }


        }


    }



}
