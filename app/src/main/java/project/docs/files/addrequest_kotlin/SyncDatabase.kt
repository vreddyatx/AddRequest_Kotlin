package project.docs.files.addrequest_kotlin

import org.json.JSONArray
import org.json.JSONException
import project.docs.files.addrequest_kotlin.data.AppDatabase
import project.docs.files.addrequest_kotlin.data.Ticket

object SyncDatabase {


    fun insertTickets(jsonArray: JSONArray) {

        for (i in 0 until jsonArray.length()) try {

            val jsonObject = jsonArray.getJSONObject(i)

            val ticket = Ticket()

            ticket.ticketId = jsonObject.get("itemId") as Int
            ticket.ticketTitle = jsonObject.get("itemName").toString()
            ticket.ticketDescription = jsonObject.get("itemDescription").toString()
            ticket.ticketDate = jsonObject.get("itemDate").toString()
            ticket.userPhotoUrl = jsonObject.get("itemUrl").toString()

            Thread({
                AppDatabase.getInstance().ticketDao().insertTicket(ticket)
            }).start()

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

}