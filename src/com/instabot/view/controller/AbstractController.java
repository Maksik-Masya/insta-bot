package com.instabot.view.controller;

import javafx.scene.Node;

public abstract class AbstractController implements Controller {
    private Node view;

    public Node getView() {
        return view;
    }

    public void setView (final Node view){
        this.view = view;
    }
}
