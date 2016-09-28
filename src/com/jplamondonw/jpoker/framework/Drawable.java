package com.jplamondonw.jpoker.framework;

/**
 * A game element that can draw itself to a console.
 */
public interface Drawable
{
    /**
     * Draw the element at the specified position.
     * @param console The console to which to draw.
     * @param row The top row position.
     * @param col The left column position.
     */
    void draw(ConsoleHelper console, int row, int col);
}
