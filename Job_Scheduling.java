
import java.util.*;

/**
 * Created by TITASH MANDAL on 9/4/2017.
 */

//Each job has profit, deadline and a parameter to check if it has been added already or not
class job
{
    int profit;    //.............for profit of a job
    int deadline;    //.............for deadline of a job
    int check;
    /*************default constructor**************/
    public job(int profit,int deadline,int check)
    {
        this.profit=profit;
        this.deadline=deadline;
        this.check=check;

    }

}

class Job_Scheduling
{
    static int number_of_jobs;

    public static void main(String args[])
    {
        Scanner scr=new Scanner(System.in);
        System.out.println("Enter the number of jobs");
        number_of_jobs=scr.nextInt();

        int max=0;   // this is to find the maximum deadline

        //create an array of job objects
        job jb[]=new job[number_of_jobs];
        System.out.print("Enter job details now: ");

        for(int i=0;i<number_of_jobs;++i)
        {
            System.out.println("Enter profit and deadline: ");
            int profit=scr.nextInt();
            int deadline=scr.nextInt();
            if(max<deadline){
                max=deadline;  // assign maximum value of deadline to "max" variable
            }

            jb[i]=new job(profit,deadline,0);  //zero as third parameter to mark that initially it is unvisited
        }
//accepted jobs from user

//bubble sort in increasing order of deadlines
        for (int i = 0; i < number_of_jobs-1; i++){
            for (int j = 0; j < number_of_jobs-i-1; j++) {
                if (jb[j].profit < jb[j + 1].profit) {
                    int temp_deadline = jb[j].deadline;
                    int temp_profit=jb[j].profit;
                    jb[j].deadline = jb[j + 1].deadline;
                    jb[j].profit=jb[j+1].profit;
                    jb[j + 1].deadline = temp_deadline;
                    jb[j+1].profit=temp_profit;
                }
            }
        }

// sorting process ends

/******************************Displaying the jobs to the user***********************/
        System.out.println("The jobs are as follows ");
        for(int i=0;i<number_of_jobs;i++)
            System.out.println("Job "+i+" Profit = "+jb[i].profit+" Deadline = "+jb[i].deadline);
// jobs displayed to the user

        int index=0;
        int hold[]=new int[max];
        for(int i=0;i<max;++i){
            hold[i]=0;
        }


/**Process of job sequencing begins*/
        for(int i=0;i<max;i++)
        {
            for(int j=0;j<number_of_jobs;j++){
                //check if it's deadline is the required deadline or not and if it has been added or not
                if(jb[j].deadline==i+1 && jb[j].check==0){
                    hold[i]=jb[j].profit;
                    jb[j].check=1;
                    break;
                }
                System.out.print(jb[j].profit);
            }
            System.out.println();
        }
/******************************calculating max profit**********************************/
        int profit=0;
        for(int i=0;i<max;++i)
            profit+=hold[i];
        System.out.println("The maximum profit is "+profit);

    }
}
