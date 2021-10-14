import java.util.Scanner;
import java.util.Random;

class oblig1 {
       public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rnd = new Random();

        //System.out.println("How many cities do you want to run the algorithm with?");
        //int l = input.nextInt();
        int l = 10;
        int[][] city = new int [l][l]; //creates a new 2d array for cities

        for(int i=0; i<city.length; i++) { //fills the cities array with zeros
            for (int j = 0; j < city.length; j++)
                city[i][j] = 0;
        }//for

        for(int i=0; i< city.length;i++) { //fills the cities array with random numbers to simulate cost
            for (int j = 0; j < city.length; j++) {
                int num = rnd.nextInt(30);
                city[i][j] = num+1;
                city[j][i] = num+1;
                if (i == j) {
                    city[i][j] = 0;
                    city[j][i] = 0;
                }//if
                System.out.println("Distance from city " + (i + 1) + " to city " + (j + 1) + " is " + city[i][j]);
            }//for
        }//for
        //random(city);
        greedy(city);
       }//method main

        public static void random(int[][] city) {
            long startTime = System.currentTimeMillis();//creating a start timestamp
            int[] visited = new int[city.length]; //creates a new 1d array
            for (int i = 0; i < visited.length; i++) { //Makes every value in visited zero.
                visited[i] = 0;
            }//for

            int cost = 0;
            Random rnd = new Random();
            int start = rnd.nextInt(city.length);//choosing a random start city
            int startCity= start;//initializing and storing variables
            int endCity=0;
            for (int counter = 0; counter < city.length*city.length; counter++) {
                int goal = rnd.nextInt(city.length); //setting a random end city
                if (visited[goal] == 0 && start != goal){
                    cost = cost + city[start][goal]; //adding cost
                    visited[start] = 1; //marking the start and end city as visited
                    visited[goal] = 1;
                    start = goal;
                    endCity = goal;
                }//if
            }//for
            long endTime = System.currentTimeMillis();//creating the end timestamp
            System.out.println("RANDOM METHOD: " +
                    " \n\tStart city: "+startCity+
                    " \n\tEnd city: "+endCity+
                    " \n\tTotal cost: "+(cost+city[endCity][startCity])+
                    " \n\tTotal Time: "+(endTime-startTime)+"ms.");
        }//method random

    public static void greedy(int[][] city) {
        long startTime = System.currentTimeMillis();//creating a start timestamp
        int[] visited = new int[city.length]; //creates a new 1d array
        for (int i = 0; i < visited.length; i++) { //Makes every value in visited zero.
            visited[i] = 0;
        }//for

        Random rnd = new Random();
        int start = rnd.nextInt(city.length);
        int goal = 0;
        int lowest = city[start][0] + 100;
        for (int i = 0; i< city.length; i++){
            if (city[start][i]<lowest && i != start && city[start][i] != 0)
                lowest = city[start][i];
        }
        System.out.println("From city "+(start+1)+" to city "+(goal+1)+" the cost is: "+lowest);
        System.out.println("Check: "+city[start][goal]);

//        System.out.println("GREEDY METHOD: " +
//                " \n\tStart city: "+startCity+
//                " \n\tEnd city: "+endCity+
//                " \n\tTotal cost: "+(cost+city[endCity][startCity])+
//                " \n\tTotal Time: "+(endTime-startTime)+"ms.");
    }//method greedy
}//class oblig1