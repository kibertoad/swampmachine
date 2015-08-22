package net.kiberion.mvc;

public interface PostInjectionProcessed {

    //Injected fields cannot be access via constructor, so should be called afterwards
    public void postInjection ();
}
