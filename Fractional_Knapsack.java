import java.util.Scanner;

/**
 * Created by TITASH MANDAL on 9/4/2017.
 */
class jobs{
    int weight;
    int value;
    double density;
    public jobs(int weight,int value,double density){
        this.weight=weight;
        this.value=value;
        this.density=density;
    }
}

public class Fractional_Knapsack {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number_of_jobs;
        System.out.print("Enter number of jobs: ");
        number_of_jobs=sc.nextInt();
        jobs[] jobObject=new jobs[number_of_jobs];
        int value;
        int weight;
        double density;
        for(int i=0;i<number_of_jobs;i++){
            System.out.println("enter Weight followed by Value");
            weight=sc.nextInt();
            value=sc.nextInt();
            density=value/(double)weight;
            jobObject[i]=new jobs(weight,value,density);

        }
        //bubble sort in increasing order of deadlines
        for (int i = 0; i < number_of_jobs-1; i++){
            for (int j = 0; j < number_of_jobs-i-1; j++) {
                if (jobObject[j].density < jobObject[j + 1].density) {

                    double temp_density = jobObject[j].density;
                    int temp_value=jobObject[j].value;
                    int temp_weight=jobObject[j].weight;
                    jobObject[j].density = jobObject[j + 1].density;
                    jobObject[j].value=jobObject[j+1].value;
                    jobObject[j].weight = jobObject[j+1].weight;
                    jobObject[j+1].density=temp_density;
                    jobObject[j+1].weight=temp_weight;
                    jobObject[j+1].value=temp_value;
                }
            }
        }

        for(int i=0;i<number_of_jobs;i++){
            System.out.println("Job "+i+" Density = "+jobObject[i].density+" Weight = "+jobObject[i].weight+" Value ="+jobObject[i].value);
        }
        System.out.println("Enter the maximum weight capacity of the Knapsack");
        int MaxWeight=sc.nextInt();
        int weightTillNow=0;
        int final_Value=0;
        int index=0;

        for(int i=0;i<number_of_jobs;i++){
            //Else if you can take the entire object at once
            if(jobObject[i].weight+weightTillNow<=MaxWeight){

                weightTillNow=weightTillNow+jobObject[i].weight;
                final_Value=final_Value+jobObject[i].value;
                System.out.println(final_Value+" "+weightTillNow);

            }
            // If we can't add current Item, add fractional part of it
            else
            {
                int remaining = MaxWeight - weightTillNow;
                final_Value += jobObject[index].value * ((double) remaining / jobObject[index].weight);

            }

        }
        System.out.println("Total Value is: "+final_Value);
    }
}

