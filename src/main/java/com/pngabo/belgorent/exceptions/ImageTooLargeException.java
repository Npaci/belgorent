package com.pngabo.belgorent.exceptions;

public class ImageTooLargeException extends RuntimeException{
    public ImageTooLargeException() {
        super("Image trop volumineuse (Max: 16Mo)");
    }
}
