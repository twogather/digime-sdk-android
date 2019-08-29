package me.digi.sdk

import android.app.Activity
import android.content.Context
import me.digi.sdk.callbacks.DMEAccountsCompletion
import me.digi.sdk.callbacks.DMEAuthorizationCompletion
import me.digi.sdk.callbacks.DMEFileContentCompletion
import me.digi.sdk.entities.DMEClientConfiguration
import me.digi.sdk.entities.DMEDataRequest
import me.digi.sdk.entities.DMESDKAgent
import me.digi.sdk.entities.DMEScope
import me.digi.sdk.entities.api.DMESessionRequest
import me.digi.sdk.interapp.managers.DMENativeConsentManager

class DMEPullClient(val context: Context, val configuration: DMEClientConfiguration): DMEClient(context, configuration) {

    val nativeConsentManager: DMENativeConsentManager by lazy { DMENativeConsentManager(sessionManager, configuration.appId) }

    fun authorize(fromActivity: Activity, completion: DMEAuthorizationCompletion) {

        val req = DMESessionRequest(configuration.appId, configuration.contractId, DMESDKAgent(), "gzip", null)
        sessionManager.getSession(req) { session, error ->

            if (session != null) {
                nativeConsentManager.beginAuthorization(fromActivity, completion)
            }
            else {
                completion(null, error)
            }
        }

    }

    fun authorize(scope: DMEDataRequest, completion: DMEAuthorizationCompletion) {

    }

    fun getSessionData(downloadHandler: DMEFileContentCompletion, completion: (DMEError?) -> Unit) {

    }

    fun getSessionData(fileId: String, completion: DMEFileContentCompletion) {

    }

    fun getSessionAccounts(completion: DMEAccountsCompletion) {

        val currentSession = sessionManager.currentSession

        if (currentSession != null && sessionManager.isSessionValid()) {

            apiClient.makeCall(apiClient.argonService.getAccounts(currentSession.key), completion)

        }
        else {
            completion(null, DMEAuthError.InvalidSession())
        }

    }

}