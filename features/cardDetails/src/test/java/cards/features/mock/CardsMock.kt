package cards.features.mock

import cards.core.model.RequestState
import cards.features.carddetails.model.CardDetails
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

object CardsMock {

    fun createSuccessResponse(): Response<CardDetails> {
        val card = CardDetails("431","asdas","11/11","123,00","200,00")
        return Response.success(card)
    }

    fun createFailureResponse(): Response<CardDetails> {
        return Response.error(404, ResponseBody.create(MediaType.get("application/json"), "Error in getting card"))
    }

    fun createSuccessRequestState(): RequestState.Success<CardDetails> {
        val card = CardDetails("431","asdas","11/11","123,00","200,00")
        return RequestState.Success(card)
    }

    fun createFailureRequestState(): RequestState.Failure<CardDetails> {
        return RequestState.Failure(Error("Error in getting card"))
    }

}