package com.ppai.ppai_dsi.domain.iterators;

public interface IIterador {
    void primero();
    void siguiente();
    boolean haTerminado();
    Object actual();
}
