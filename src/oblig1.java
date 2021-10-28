import java.util.Scanner;
import java.util.Random;

class oblig1 {
       public static void main(String[] args) { //TODO implement try catch to convert to bigint - fourth
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
                //System.out.println("Distance from city " + (i + 1) + " to city " + (j + 1) + " is " + city[i][j]);
            }//for
        }//for
            //random(city);
            int[] orderGreedy = greedy(city);
            greedyRandom(city, orderGreedy);
        }//method main

        public static int[] random(int[][] city) {
            long startTime = System.nanoTime();//creating a start timestamp
            int[] visited = new int[city.length]; //creates a new 1d array TODO maybe make a method that creates this array
            int[] orderRandom = new int[city.length];//creates a mew 1d array to hold the final order
            for (int i = 0; i < visited.length; i++) { //Makes every value in visited zero.
                visited[i] = 0;
                orderRandom[i] = 0;
            }//for

            int cost = 0;
            Random rnd = new Random();
            int start = rnd.nextInt(city.length);//choosing a random start city
            int startCity= start;//initializing and storing variables
            int endCity=0;
            for (int i = 0; i < city.length/*city.length*/; i++) {
                int goal = rnd.nextInt(city.length); //setting a random end city
                if (visited[goal] == 0 && start != goal){
                    cost = cost + city[start][goal]; //adding cost
                    visited[start] = 1; //marking the start and end city as visited
                    visited[goal] = 1;
                    start = goal;
                    endCity = goal;
                    orderRandom[i] = start; //sets the chosen city in order
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
            return orderRandom;
        }//method random


    public static int[] greedy(int[][] city) {
        long startTime = System.nanoTime();//creating a start timestamp
        int[] visited = new int[city.length]; //creates a new 1d array
        int[] orderGreedy = new int[city.length];//creates a mew 1d array to hold the final order
        for (int i = 0; i < visited.length; i++) { //Makes every value in visited zero.
            visited[i] = 0;
            orderGreedy[i] = 0;
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

            orderGreedy[i] = start; //sets the chosen city in order
            orderGreedy[i+1] = goal;

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
        return orderGreedy;
    }//method greedy

    public static int[] greedyRandom(int [][] city, int[] order){ //TODO implement random and an array for the solution first
       long startTime = System.nanoTime();//creating a start timestamp
       int[] greedyRandomOrder = new int [order.length];//creates a 1d array to store the new order in
        for (int i = 0; i<greedyRandomOrder.length; i++) { //fills the new array with zeros
            greedyRandomOrder[i] = 0;
            greedyRandomOrder[i] = order[i]; //and then with the equivalent values of the order array
        }
        int orgCost = 0;
        int oldCost = 0;

        int n = 1000;
        for (int i = 0; i < n; i++ ) {
            Random rnd = new Random(); //choosing two random indexes of order
            int index1 = rnd.nextInt(order.length);
            int index2 = rnd.nextInt(order.length);

            int tmp = 0;

            tmp = greedyRandomOrder[index1];
            greedyRandomOrder[index1] = greedyRandomOrder[index2];
            greedyRandomOrder[index2] = tmp;

//            System.out.println("Order\tGreedyRandomOrder");
//            for (int j = 0; j < order.length; j++)
//                System.out.println(order[j] + "\t\t" + greedyRandomOrder[j]);

            orgCost = costCalculation(city, order);
            oldCost = orgCost;
            int newCost = costCalculation(city, greedyRandomOrder);
            if (oldCost > newCost) {
                order = greedyRandomOrder;
                oldCost = newCost;
                System.out.println("New solution found. Cost: " + oldCost);
            }
            //System.out.println("Org: " + oldCost + "\tNew: " + newCost);
        }

        long endTime = System.nanoTime();//creating the end timestamp
        long ns = endTime-startTime;
        long ms = ns/100000;
        long s = ns/1000000000;
        int startCity = order[0];
        int endCity = order[order.length-1];
        System.out.println("\nGREEDY RANDOM METHOD: " +
                " \n\tStart city: "+startCity+
                " \n\tEnd city: "+endCity+
                " \n\tOriginal cost: "+orgCost+
                " \n\tNew cost: "+oldCost+
                " \n\tTotal Time: "+ns+"ns, or "+ms+"ms, or "+s+"s.");
        return greedyRandomOrder;
    }//method greedyRandom




    public static int costCalculation(int[][] city, int[] order){ //method to calculate cost based on the city array and the order
        int orgCost = 0;
        int lastCity= 0;
        for (int i = 0; i< order.length-1; i++) {   //calculates the cost
            orgCost = orgCost + city[order[i]][order[i + 1]];
            lastCity=order[i+1];
        }//for
        orgCost = orgCost +city[lastCity][order[0]];
        //System.out.println("Total Cost: "+orgCost);
        return orgCost;
    }//method costCalculation
}//class oblig1