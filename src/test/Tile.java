package test;


import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Tile {
    public final char letter;
    public final int score;

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    public static class Bag {
       protected int[] letterCount;
       protected Tile[] score;
       private static Bag newBag=null;
       private final int[] letterCountRulse;

        private Bag() {
            letterCount = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
            letterCountRulse=letterCount.clone();
            score = new Tile[]{new Tile('A', 1), new Tile('B', 3), new Tile('C', 3),
                    new Tile('D', 2), new Tile('E', 1), new Tile('F', 4),
                    new Tile('G', 2), new Tile('H', 4), new Tile('I', 1),
                    new Tile('J', 8), new Tile('K', 5), new Tile('L', 1),
                    new Tile('M', 3), new Tile('N', 1), new Tile('O', 1),
                    new Tile('P', 3), new Tile('Q', 10), new Tile('R', 1),
                    new Tile('S', 1), new Tile('T', 1), new Tile('U', 1),
                    new Tile('V', 4), new Tile('W', 4), new Tile('X', 8),
                    new Tile('Y', 4), new Tile('Z', 10),};
        }

        public Tile getRand() {
            int randomIndex;
            Random random = new Random();
            randomIndex = random.nextInt(letterCount.length);
            boolean allZero = true;
            for (int count : letterCount) {
                if (count != 0) {
                    allZero = false;
                    break;
                }
            }

            // If all cells are 0, return null
            if (allZero) {
                return null;
            }

            if (letterCount[randomIndex] == 0) {
                while (letterCount[randomIndex] == 0)
                    randomIndex = random.nextInt(letterCount.length);
            }
            letterCount[randomIndex]--;
            return score[randomIndex];

        }

        public Tile getTile(char letter){
            int index = (int)letter - 'A';
            if (index < 0 || index > 25) {
                return null;
            }
            else if(letterCount[index] == 0){
                return null;
            }
             else return score[index];
        }

        public void put(Tile tile){
            int index = (int)tile.letter - 'A';
            letterCount[index]++;
            if(letterCount[index] > letterCountRulse[index]){
                letterCount[index]--;
            }
        }

        public int size(){
            int sum=0;
            for (int i=0; i<letterCount.length; i++) {
                sum+=letterCount[i];
            }
            return sum;
        }

        public int[] getQuantities(){
            return letterCount.clone();
        }

        public static Bag getBag(){
            if(newBag==null){
                newBag=new Bag();
            }
            return newBag;
        }
    }
}
