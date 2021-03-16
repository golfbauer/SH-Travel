package de.hhn.se.labswp.wstgsh.exceptions;

public class ReisepunktNotFoundException extends RuntimeException{
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundException.class);

    public ReisepunktNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
