import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static char[][] board = new char[3][3];
    static int Xcord, Ycord;

    public static void main(String[] args) {
        fillTable(board);
        drawTable(board);
        int count = 0;

        while (getResult(board).equals("")) {
            getCoordinates(count);
            drawTable(board);
            count++;
        }

        System.out.println(getResult(board));
    }

    static boolean isInt(String s) {
        try {
            String[] pieces = s.split(" ");
            int i = Integer.parseInt(pieces[0]);
            int j = Integer.parseInt(pieces[1]);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }
    }

    static void getCoordinates(int counter){ // gets coordinates and checks whether it satisfies specific conditions

        System.out.print("Enter coordinates: ");
        while(true){

            String input = scanner.nextLine();

            while (!(isInt(input)) ){
                input = scanner.nextLine();
            }

            String[] pieces = input.split(" ");
            Xcord = Integer.parseInt(pieces[0]);
            Ycord = Integer.parseInt(pieces[1]);


            if(Xcord > 3 || Xcord < 1 || Ycord > 3 || Ycord < 1){
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                ninetyRight(board);
                if(isEmpty(board, Xcord, Ycord)){
                    if (counter % 2 == 0) {
                        fillValue(board, 'X', Xcord, Ycord);
                    } else {
                        fillValue(board, 'O', Xcord, Ycord);
                    }

                    ninetyLeft(board);
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
                ninetyLeft(board);
            }
        }
    }

    static void drawTable(char[][] arr){    // draws matrix as a table
        System.out.println("---------");
        for(int i = 0; i < 3; i++){
            System.out.print("| ");
            for(int j = 0; j < 3; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static void fillTable(char[][] arr){ // fills the whole matrix by the String put by user
        String input = "         ";
        int counter = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                arr[i][j] = input.charAt(counter);
                counter++;
            }
        }
    }

    static char[][] fillValue(char[][] arr, char value, int X, int Y){ // fills matrix with a specific value
        arr[X-1][Y-1] = value;
        return arr;
    }

    static char[][] ninetyRight(char[][] arr){ // rotates matrix to 90 degree right
        char[][] carr = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                carr[i][j] = arr[i][j];
            }
        }

        int k = 2;
        for(int i = 0; i < 3; i++){
            arr[0][i] = carr[k][0];
            arr[1][i] = carr[k][1];
            arr[2][i] = carr[k][2];
            k--;
        }
        return arr;
    }

    static char[][] ninetyLeft(char[][] arr){ // rotates matrix to 90 degree left
        for(int i = 0; i < 3; i++){
            ninetyRight(arr);
        }
        return arr;
    }

    static boolean isEmpty(char[][] arr, int X, int Y){  // checks whether the chosen coordinates are empty
        return (arr[X-1][Y-1] == ' ' || arr[X-1][Y-1] == '_');
    }

    // 8. Print result
    static String getResult(char[][] arr) {
        String result = "";
        int sum = 0;
        int xResult = 264; // 'X' + 'X' + 'X'
        int oResult = 237; // 'O' + 'O' + 'O'
        boolean finished = false;

        // horizontal
        for (char[] value : arr) {
            for (int j = 0; j < arr.length; j++) {
                sum += value[j];

                if (sum == oResult) {
                    result = "O wins";
                    finished = true;
                }

                if (sum == xResult) {
                    result = "X wins";
                    finished = true;
                }
            }
            sum = 0;
        }

        // vertical
        for (int i = 0; i < arr.length; i++) {
            for (char[] chars : arr) {
                sum += chars[i];

                if (sum == oResult) {
                    result = "O wins";
                    finished = true;
                }

                if (sum == xResult) {
                    result = "X wins";
                    finished = true;
                }
            }
            sum = 0;
        }

        int firstDiagonal = 0;
        int secondDiagonal = 0;

        // diagonal
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                firstDiagonal = arr[0][2] + arr[1][1] + arr[2][0];
                secondDiagonal = arr[0][0] + arr[1][1] + arr[2][2];

                if (firstDiagonal == oResult || secondDiagonal == oResult) {
                    result = "O wins";
                    finished = true;
                }

                if (firstDiagonal == xResult || secondDiagonal == xResult) {
                    result = "X wins";
                    finished = true;
                }
            }
        }

        int countX = 0;
        int countO = 0;

        // draw
        for (char[] chars : arr) {
            for (int j = 0; j < arr.length; j++) {
                if (chars[j] == 'X') {
                    countX++;
                } else if (chars[j] == 'O') {
                    countO++;
                }
                if (countX + countO == 9 && !finished) {
                    result = "Draw";
                    finished = true;
                }
            }
        }
        return result;
    }
}
