package model.AI;

import java.util.ArrayList;
import java.util.Random;

public class Brain {
    private ArrayList<Boolean> moves = new ArrayList<>();

    public int getMovesSize() {
        return moves.size();
    }

    public Brain(int playerLevel) {
        Mathematics mathematics = new Mathematics();
        insertMoves(mathematics.calculatePercentage(playerLevel, 20));
    }

    private void insertMoves(int percentage) {
        Random random = new Random();
        boolean move;

        for (int i = 0; i < 20; i++) {
            if (random.nextInt(101) >= percentage)
            {
                move = true;
            }
            else
            {
                move = random.nextBoolean();
            }
            moves.add(move);
        }

        for(boolean x : moves)
        {
            System.out.println(x);
        }
    }
}
