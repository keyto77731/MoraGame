package com.example.moragame.game;

public enum WinState {
    PLAYER_WIN,
    COMPUTER_WIN,
    EVEN,
    IDLE;

    public static WinState getWinState(Mora player, Mora computer) {
        if (player == computer) {
            return WinState.EVEN;
        }

        if ((computer == Mora.SCISSOR && player == Mora.PAPER) || player==Mora.NONE) {
            return WinState.COMPUTER_WIN;
        }

        if (player == Mora.SCISSOR && computer == Mora.PAPER || computer==Mora.NONE) {
            return WinState.PLAYER_WIN;
        }



        //最後依照順序比對 剪刀<石頭<布
        if (player.ordinal() > computer.ordinal()) {
            return WinState.PLAYER_WIN;
        }

        return WinState.COMPUTER_WIN;
    }
}
