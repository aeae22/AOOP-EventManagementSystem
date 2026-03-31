package view;

import controller.AttendeeController;
import controller.EventController;
import controller.RegistrationController;
import model.Attendee;
import model.Event;
import model.EventOrder;
import model.TicketCart;
import model.TicketType;

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
        int choice;

        do {
            System.out.println("\n=== Event Management System ===");
            System.out.println("1. Add event");
            System.out.println("2. View events");
            System.out.println("3. Register attendee");
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
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addEvent() {
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

        TicketType selectedType = event.getTicketTypes().get(0);

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        TicketCart cart = new TicketCart();
        cart.addTicket(event, selectedType, quantity);

        EventOrder order = registrationController.checkout(attendee, cart, "2026-03-31");

        System.out.println("Registration successful.");
        System.out.println(order);
    }
}