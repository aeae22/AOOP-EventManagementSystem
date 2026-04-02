package utils;

import model.Attendee;
import model.Event;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileExportUtil {

    public static void exportAttendeesToTXT(Event event, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));

        writer.println("Attendees for event: " + event.getTitle());
        writer.println("Date: " + event.getDate());
        writer.println("-------------------------");

        for (Attendee attendee : event.getAttendees()) {
            writer.println("Name: " + attendee.getName());
            writer.println("Email: " + attendee.getEmail());
            writer.println("Address: " + attendee.getAddress());
            writer.println();
        }

        writer.close();
    }

    public static void exportAttendeesToCSV(Event event, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));

        writer.println("Name,Email,Address");

        for (Attendee attendee : event.getAttendees()) {
            writer.println(attendee.getName() + "," + attendee.getEmail() + "," + attendee.getAddress());
        }

        writer.close();
    }

    public static void exportAttendeesToJSON(Event event, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));

        writer.println("{");
        writer.println("  \"eventTitle\": \"" + event.getTitle() + "\",");
        writer.println("  \"date\": \"" + event.getDate() + "\",");
        writer.println("  \"attendees\": [");

        for (int i = 0; i < event.getAttendees().size(); i++) {
            Attendee attendee = event.getAttendees().get(i);

            writer.println("    {");
            writer.println("      \"name\": \"" + attendee.getName() + "\",");
            writer.println("      \"email\": \"" + attendee.getEmail() + "\",");
            writer.println("      \"address\": \"" + attendee.getAddress() + "\"");

            if (i == event.getAttendees().size() - 1) {
                writer.println("    }");
            } else {
                writer.println("    },");
            }
        }

        writer.println("  ]");
        writer.println("}");

        writer.close();
    }
}