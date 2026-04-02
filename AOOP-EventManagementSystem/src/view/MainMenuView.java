//Menu-driven interface for system navigation//
package view;

import utils.FileExportUtil;
import controller.AttendeeController;
import controller.EventController;
import controller.RegistrationController;
import model.Attendee;
import model.Event;
import model.EventOrder;
import model.TicketCart;
import model.TicketType;

import java.io.IOException;
import java.util.Scanner;

public class MainMenuView {
    private EventController eventController;
    private AttendeeController attendeeController;
    private RegistrationController registrationController;
    private Scanner scanner;

    public MainMenuView(EventController eventController, AttendeeController attendeeController,
                        RegistrationController registrationController) {
        this.eventController = eventController;
        this.attendeeController = attendeeController;
        this.registrationController = registrationController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice = -1;

        do {
            try {
                System.out.println("\n=== Event Management System ===");
                System.out.println("1. Add event");
                System.out.println("2. View events");
                System.out.println("3. Register attendee");
                System.out.println("4. Export attendee list");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");

                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addEvent();
                        break;
                    case 2:
                        viewEvents();
                        break;
                    case 3:
                        registerAttendee();
                        break;
                    case 4:
                        exportAttendees();
                        break;
                    case 0:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (choice != 0);
    }

    private void addEvent() {
        try {
            System.out.print("Enter event title: ");
            String title = scanner.nextLine();

            System.out.print("Enter event date: ");
            String date = scanner.nextLine();

            System.out.print("Enter base price: ");
            double basePrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter capacity: ");
            int capacity = Integer.parseInt(scanner.nextLine());

            Event event = new Event(title, date, basePrice, capacity);

            TicketType regular = new TicketType("Regular", 1.0);
            TicketType vip = new TicketType("VIP", 1.5);

            event.addTicketType(regular);
            event.addTicketType(vip);

            eventController.addEvent(event);
            System.out.println("Event added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewEvents() {
        if (eventController.getEvents().isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        for (Event event : eventController.getEvents()) {
            System.out.println(event);
        }
    }

    private void registerAttendee() {
        try {
            System.out.print("Enter attendee name: ");
            String name = scanner.nextLine();

            System.out.print("Enter attendee email: ");
            String email = scanner.nextLine();

            System.out.print("Enter attendee address: ");
            String address = scanner.nextLine();

            Attendee attendee = new Attendee(name, email, address);

            System.out.print("Enter event title: ");
            String eventTitle = scanner.nextLine();

            Event event = eventController.findEventByTitle(eventTitle);

            if (event == null) {
                System.out.println("Event not found.");
                return;
            }

            if (event.getTicketTypes().isEmpty()) {
                System.out.println("No ticket types available for this event.");
                return;
            }

            System.out.println("Available ticket types:");
            for (int i = 0; i < event.getTicketTypes().size(); i++) {
                TicketType type = event.getTicketTypes().get(i);
                System.out.println((i + 1) + ". " + type.getName());
            }

            System.out.print("Choose ticket type: ");
            int ticketChoice = Integer.parseInt(scanner.nextLine());

            if (ticketChoice < 1 || ticketChoice > event.getTicketTypes().size()) {
                System.out.println("Invalid ticket type choice.");
                return;
            }

            TicketType selectedType = event.getTicketTypes().get(ticketChoice - 1);

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter order date: ");
            String orderDate = scanner.nextLine();

            TicketCart cart = new TicketCart();
            cart.addTicket(event, selectedType, quantity);

            EventOrder order = registrationController.checkout(attendee, cart, orderDate);

            System.out.println("Registration successful.");
            System.out.println(order);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void exportAttendees() {
        try {
            System.out.print("Enter event title: ");
            String eventTitle = scanner.nextLine();

            Event event = eventController.findEventByTitle(eventTitle);

            if (event == null) {
                System.out.println("Event not found.");
                return;
            }

            System.out.println("Choose export format:");
            System.out.println("1. TXT");
            System.out.println("2. CSV");
            System.out.println("3. JSON");
            System.out.print("Your choice: ");
            int formatChoice = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter file name: ");
            String fileName = scanner.nextLine();

            if (formatChoice == 1) {
                FileExportUtil.exportAttendeesToTXT(event, fileName);
                System.out.println("Attendees exported to TXT successfully.");
            } else if (formatChoice == 2) {
                FileExportUtil.exportAttendeesToCSV(event, fileName);
                System.out.println("Attendees exported to CSV successfully.");
            } else if (formatChoice == 3) {
                FileExportUtil.exportAttendeesToJSON(event, fileName);
                System.out.println("Attendees exported to JSON successfully.");
            } else {
                System.out.println("Invalid export format.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
