package com.ACMD.app.Graphic_Layer.GUI;

import java.awt.event.ActionListener;

/**
 * Rappresenta un generico Frame
 */
public interface Frame extends ActionListener{
    /**
     * Inizializza le componenti, in questa classe è non implementato
     */
    default void initComponents(){}

}