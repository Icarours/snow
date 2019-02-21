package com.syl.snow.help;

/**
 * Created by Bright on 2019/2/21.
 *
 * @Describe 枚举类举例
 * RecyclerView 的位置
 * @Called
 */
public enum Position {
    LEFT(0), CENTER(1), RIGHT(2);
    private int value;

    Position(int i) {
        value = i;
    }

    public Position valueOf(int value) {
        switch (value) {
            case 0:
                return Position.LEFT;
            default:
            case 1:
                return Position.CENTER;
            case 2:
                return Position.RIGHT;
        }
    }
}
