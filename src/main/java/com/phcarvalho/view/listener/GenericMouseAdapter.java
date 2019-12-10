package com.phcarvalho.view.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class GenericMouseAdapter extends MouseAdapter {

    private Runnable mouseOnceClickedRunnable;

    public GenericMouseAdapter(Runnable mouseOnceClickedRunnable) {
        this.mouseOnceClickedRunnable = mouseOnceClickedRunnable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getClickCount() == 1)
            mouseOnceClickedRunnable.run();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }
}
