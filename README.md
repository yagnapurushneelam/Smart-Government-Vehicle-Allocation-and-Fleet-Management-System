[README.md](https://github.com/user-attachments/files/34420249/README.md)
# Smart Government Vehicle Allocation and Fleet Management System

A Java-based, menu-driven console application designed to demonstrate
organized management of government vehicle requests and fleet
allocation.

## Project Overview

The **Smart Government Vehicle Allocation and Fleet Management System**
manages politician records, government vehicles, vehicle requests,
allocation decisions, returns, and maintenance-related operations
through a console interface.

The project demonstrates Core Java, object-oriented programming, and the
Java Collections Framework. It is an academic prototype and is not an
officially deployed government system.

## Key Features

-   Register and manage politician records, including MLA, MP, and
    Minister types.
-   Register, search, and remove vehicle records.
-   Submit vehicle allocation requests.
-   Process requests using priority-based handling.
-   Approve or reject requests and manage cancellations.
-   Track allocations and allocation history.
-   Record vehicle returns.
-   Support vehicle sorting and maintenance-related operations.
-   Handle invalid input and application-specific errors through
    exception handling.

> The exact operations available depend on the options implemented in
> the application menu.

## Technologies and Concepts

-   **Language:** Java
-   **Core Java:** Classes, objects, inheritance, encapsulation,
    polymorphism, and interfaces
-   **Collections:** Java Collections Framework
-   **Exception handling:** Built-in and custom exceptions
-   **Interface:** Menu-driven console application

## Project Structure

The source code is organized into packages similar to the following:

``` text
src/
├── main/
│   └── Main.java
├── model/
│   ├── Politician.java
│   ├── MLA.java
│   ├── MP.java
│   ├── Minister.java
│   ├── Vehicle.java
│   ├── VehicleRequest.java
│   └── Allocation.java
├── interfaces/
│   └── VehicleAllocation.java
├── service/
│   ├── PoliticianService.java
│   ├── VehicleService.java
│   └── AllocationService.java
├── exception/
│   ├── VehicleUnavailableException.java
│   └── RecordNotFoundException.java
└── util/
    └── InputUtil.java
```

*This is an illustrative package layout. Refer to the actual `src`
folder in the repository for the exact files and names.*

## Requirements

-   Java Development Kit (JDK) 17 or later
-   Eclipse IDE or another Java IDE

## How to Run

### Using Eclipse IDE

1.  Clone or download this repository.
2.  Open Eclipse.
3.  Select **File → Import → General → Existing Projects into
    Workspace**.
4.  Choose the downloaded project folder and complete the import.
5.  Expand `src` and locate `Main.java` in the `main` package.
6.  Right-click `Main.java` and select **Run As → Java Application**.
7.  Follow the menu displayed in the console.

If your local project uses a different package layout, run the class
containing the application's `main` method.

## Data Storage

This version uses in-memory data structures. Records created while the
application is running are not permanently stored and will be lost when
the application exits. A database or file-based persistence layer is not
included in this version.

## Future Enhancements

-   Integrate a database for persistent storage.
-   Add user authentication and role-based access.
-   Build a web-based interface for administrators.
-   Add reports and analytics for fleet utilization.
-   Integrate GPS-based vehicle tracking.

## Project Status

Academic Core Java console application.

## Author

**Neelam Yagna Purush**
