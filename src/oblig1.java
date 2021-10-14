import java.util.Scanner;
import java.util.Random;

class oblig1 {
       public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rnd = new Random();

        System.out.println("How many cities do you want to run the algorithm with?");
        int l = input.nextInt();
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
                //System.out.println("Distance from city " + (i + 1) + " to city " + (j + 1) + " is " + city[i][j]);
            }//for
        }//for
        random(city);
        greedy(city);
       }//method main

        public static void random(int[][] city) {
            long startTime = System.nanoTime();//creating a start timestamp
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
            long endTime = System.nanoTime();//creating the end timestamp

            long ns = endTime-startTime;
            long ms = ns/100000;
            long s = ns/1000000000;

            System.out.println("RANDOM METHOD: " +
                    " \n\tStart city: "+startCity+
                    " \n\tEnd city: "+endCity+
                    " \n\tTotal cost: "+(cost+city[endCity][startCity])+
                    " \n\tTotal Time: "+ns+"ns, or "+ms+"ms, or "+s+"s.");
        }//method random


    public static void greedy(int[][] city) {
        long startTime = System.nanoTime();//creating a start timestamp
        int[] visited = new int[city.length]; //creates a new 1d array
        for (int i = 0; i < visited.length; i++) { //Makes every value in visited zero.
            visited[i] = 0;
        }//for

        Random rnd = new Random();//Chooses a random starting city
        int start = rnd.nextInt(city.length);
        int startCity = start;
        int goal = 0;
        int lowest;
        int cost = 0;

        for(int i = 0; i< (city.length-1); i++) {
            lowest = city[start][0] + 100;
            for (int j = 0; j < city.length; j++) { //calculates which city has the least cost to move to
                if (city[start][j] < lowest && j != start && city[start][j] != 0 && visited[j] == 0) {
                    lowest = city[start][j];
                    goal = j;
                }//if
            }//for
            visited[start] = 1;//marks the start city as visited
            visited[goal] = 1;//marks the goal city as visited
            start = goal; //moves to the chosen city
            cost = cost + lowest;
        }//for

        int endCity = goal;
        long endTime = System.nanoTime();//creating the end timestamp

        long ns = endTime-startTime;
        long ms = ns/100000;
        long s = ns/1000000000;

        System.out.println("\nGREEDY METHOD: " +
                " \n\tStart city: "+startCity+
                " \n\tEnd city: "+endCity+
                " \n\tTotal cost: "+(cost+city[endCity][startCity])+
                " \n\tTotal Time: "+ns+"ns, or "+ms+"ms, or "+s+"s.");
    }//method greedy
}//class oblig1