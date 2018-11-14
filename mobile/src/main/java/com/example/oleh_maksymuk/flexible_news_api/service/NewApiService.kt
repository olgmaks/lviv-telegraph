package com.example.oleh_maksymuk.flexible_news_api.service;

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.oleh_maksymuk.flexible_news_api.constants.COUNTRY_PREFERENCE_KEY
import com.example.oleh_maksymuk.flexible_news_api.constants.COUNTRY_PREFERENCE_VALUE_US
import com.example.oleh_maksymuk.flexible_news_api.constants.LANGUAGE_PREFERENCE_KEY
import com.example.oleh_maksymuk.flexible_news_api.constants.LANGUAGE_PREFERENCE_VALUE_EN
import com.example.oleh_maksymuk.flexible_news_api.service.model.GetTopLinesResponse
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.*
import com.google.gson.Gson

class NewApiService(private val context: Context) {

    private val API_KEY = "56520a27dddc43059a5165b829042580"
    private val BASE_URL = "https://newsapi.org/v2"
    private val DEFAULT_PAGE_SIZE = "100"
    private val GET_TOP_HEADLINES_URL = "$BASE_URL/top-headlines?apiKey=$API_KEY"

    fun getTopLines(responseHandler: ResponseHandler<GetTopLinesResponse>) =
        getTopLines(GetTopLinesResponseHandler(responseHandler))

    private fun getTopLines(handler: Handler<GetTopLinesResponse>): Request {
        var defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val countryPreference = defaultSharedPreferences.getString(COUNTRY_PREFERENCE_KEY, COUNTRY_PREFERENCE_VALUE_US)
        val languagePreference = defaultSharedPreferences.getString(LANGUAGE_PREFERENCE_KEY, LANGUAGE_PREFERENCE_VALUE_EN)
        val serviceUrl = "$GET_TOP_HEADLINES_URL&$COUNTRY_PREFERENCE_KEY=$countryPreference" +
                when(languagePreference) { languagePreference -> "&$LANGUAGE_PREFERENCE_KEY=$languagePreference" else -> ""}
        return Fuel
            .get(serviceUrl)
            .responseObject(GetTopLinesResponseDeserializer(), handler)
    }


    class GetTopLinesResponseDeserializer : ResponseDeserializable<GetTopLinesResponse> {

        override fun deserialize(content: String): GetTopLinesResponse? {
            return Gson().fromJson(content, GetTopLinesResponse::class.java)
        }
    }


    class GetTopLinesResponseHandler
        (private val responseHandler: ResponseHandler<GetTopLinesResponse>) : Handler<GetTopLinesResponse> {

        override fun success(request: Request, response: Response, value: GetTopLinesResponse) {
            val articles = value.articles.filter { a -> !a.urlToImage.isNullOrEmpty() }
            val getTopLinesResponse = GetTopLinesResponse(
                articles = articles,
                status = value.status,
                totalResults = value.totalResults
            )
            responseHandler.onResult(getTopLinesResponse)
        }

        override fun failure(request: Request, response: Response, error: FuelError) = Unit
    }
}
