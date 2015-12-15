package com.learning.app.engine.backend.constants;

/**
 * API Keys, Client Ids and Audience Ids for accessing APIs and configuring
 * Cloud Endpoints.
 * When you deploy your solution, you need to use your own API Keys and IDs.
 * Please refer to the documentation for this sample for more details.
 */
public final class AppConstants {
    // User: Update keys

    /**
     * Android client ID from Google Cloud console.
     */
    public static final String ANDROID_CLIENT_ID = "938739565497-6tl3ncuainvk4998hki254jg7diabfd6.apps.googleusercontent.com";

    /**
     * Web client ID from Google Cloud console.
     */
    public static final String WEB_CLIENT_ID = "938739565497-i4njb8kpphdt3kqrn6ruuc2n480ik0q5.apps.googleusercontent.com";

    /**
     * Audience ID used to limit access to some client to the API.
     */
    public static final String AUDIENCE_ID = WEB_CLIENT_ID;

    /**
     * API package name.
     */
    public static final String API_OWNER = "com.learning.app.engine.backend";

    /**
     * API package path.
     */
    public static final String API_PACKAGE_PATH = "";

    /**
     * API email Scope.
     */
    public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
}
