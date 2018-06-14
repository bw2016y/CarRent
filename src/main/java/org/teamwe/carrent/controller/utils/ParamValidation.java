package org.teamwe.carrent.controller.utils;

/**
 * Validate the parameters
 *
 * @author FDws
 * Created on 2018/6/14 17:26
 */

public class ParamValidation {
    /**
     * Validate Failed message;
     */
    private String message = "";

    /**
     * Validate status
     */
    private boolean status = true;

    private ParamValidation getInstance() {
        return new ParamValidation();
    }

    private boolean validate() {
        return status;
    }
}
