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
public class EasyGame extends GameMode implements Const
{
    
    @Override
    public void initSodokuGame()
    {
       Board b = new Board(easyLevel);
    }
}
