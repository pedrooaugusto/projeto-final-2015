package com.pedroaugusto.transicoes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class ControleTransicoes 
{
    private Component comp;
    private Motor motor = null;
    public ControleTransicoes(Component comp)
    {
        this.comp = comp;
    }
    //Expande algo sem mexer nas suas coordenadas ou cores, mexe somente em h e w
    public void manipularDimenssao(Dimension to, int time)
    {
        
        ColorRectangle a = new ColorRectangle(comp.getLocation(),
                comp.getSize(), comp.getBackground());
        ColorRectangle b = new ColorRectangle(comp.getLocation(), to,
                comp.getBackground());
        if(motor != null)
            motor.stop();
        motor = new Motor(comp, a, b, time);
        motor.start();
    }
    //Move algo atraves da tela sem mexer em sua dimensão ou cor
    public void manipularCoordenadas(Point to)
    {
        ColorRectangle a = new ColorRectangle(comp.getLocation(),
                comp.getSize(), comp.getBackground());
        ColorRectangle b = new ColorRectangle(to, comp.getSize(),
                comp.getBackground());
        if(motor != null)
            motor.stop();
        motor = new Motor(comp, a, b, 300);
        motor.start();
    }
    //Move muda a cor de algo na tela sem mexer em suas dimensões ou cores
    public void manipularCor(Color to, int time)
    {
        ColorRectangle a = new ColorRectangle(comp.getLocation(),
                comp.getSize(), comp.getBackground());
        ColorRectangle b = new ColorRectangle(comp.getLocation(),
                comp.getSize(), to);
        /*if(motor != null)
            motor.stop();*/
        motor = new Motor(comp, a, b, 300);
        motor.start();
    }
    public void manipularCor(Color to, int time, Component comp)
    {
        ColorRectangle a = new ColorRectangle(comp.getLocation(),
                comp.getSize(), comp.getBackground());
        ColorRectangle b = new ColorRectangle(comp.getLocation(),
                comp.getSize(), to);
        /*if(motor != null)
            motor.stop();*/
        motor = new Motor(comp, a, b, time);
        motor.start();
    }
    /*
        Faz uma animação completa atraves da tela mudando tudo
        -Muda cor, tamanho, dimenssão e não se preocupa com o estado inicial
        do objeto, ou seja, o quadro pode estar de fato na coordenada 23, porém
        quando vc começar a animalo pode dizer que ele começou na posição 30. O que
        vai acontecer é que ele vai pular de 23 para 30 e começar a animação a partir de 30
    */
    public void coreTransition(ColorRectangle from, ColorRectangle to, int time)
    {
        if(motor != null)
            motor.stop();
        motor = new Motor(comp, from, to, time);
        motor.start();
    }
    
    public void abrirFromZero(Dimension da, Dimension db, int time)
    {
        ColorRectangle a = new ColorRectangle(comp.getLocation(),
                da, comp.getBackground());
        ColorRectangle b = new ColorRectangle(comp.getLocation(), db,
                comp.getBackground());
        if(motor != null)
            motor.stop();
        motor = new Motor(comp, a, b, time);
        motor.start();
    }
    
    /*
        Faz a animação de abir uma nova janela, no caso expandir ela
    */
    public void abreJanela(Window frame, int x)
    {
        ColorRectangle a = new ColorRectangle(frame.getLocation(),
                new Dimension(0, (int)frame.getSize().getHeight()), Color.BLUE);
        ColorRectangle b = new ColorRectangle(frame.getLocation(),
                new Dimension(x, (int)frame.getSize().getHeight()), Color.BLUE);
        motor = new Motor(frame, a, b, 130);
        frame.setVisible(true);
        motor.start();
    }
    
    public void abreJanela(Window frame)
    {
        ColorRectangle a = new ColorRectangle(frame.getLocation(),
                new Dimension(0, (int)frame.getSize().getHeight()), Color.BLUE);
        ColorRectangle b = new ColorRectangle(frame.getLocation(),
                frame.getSize(), Color.BLUE);
        motor = new Motor(frame, a, b, 130);
        frame.setVisible(true);
        motor.start();
    }
    /*
        Faz a animação de fechar uma janela, no caso contrai-la
    */
    public void fechaJanela(final Window frame, final boolean onlyDispose)
    {
        ColorRectangle a = new ColorRectangle(frame.getLocation(),
                frame.getSize(), Color.BLUE);
        ColorRectangle b = new ColorRectangle(frame.getLocation(),
                new Dimension(0, (int) frame.getSize().getHeight()), Color.BLUE);
        motor = new Motor(frame, a, b, 130);
        motor.start();
        Timer timer = new Timer(540, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(onlyDispose)
                    frame.dispose();
                else
                    System.exit(0);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    /*
        Fecha a janela atual e abre uma nova
    */
    public void novaJanela(JFrame atual, final JFrame nova)
    {
        fechaJanela(atual, true);
        Timer timer = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abreJanela(nova);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
