/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sodokuproject;

/**
 *
 * @author lenovo
 */
public class HardGame extends GameMode implements Const
{
     @Override
    public void initSodokuGame()
    {
        new Board(hardLevel);
    }
}
