package net.kiberion.swampmachine.api.templating;

public interface TemplateFactory {

    /**
     * Produces a template from a provided body. Supported format of a body depends on a specific template engine underneath
     * the factory implementation
     * @param templateBody
     * @return
     */
    public Template produceTemplate (String templateBody);

}
