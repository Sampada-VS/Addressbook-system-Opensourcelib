package com.blz.addressbook;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

public class AddressBook {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int option;
		String input;
		String fileOperated = null;

		File directoryPath = new File(".");
		File[] files = directoryPath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});

		do {
			System.out.println(
					"== Address Book == \n 1.Create new addressbook 2.Open existing addressbook \n 3.Search using city 4.Search using state \n 5.Exit \n Enter option :");
			option = sc.nextInt();
			switch (option) {
			case 1:
				fileOperated = AddressBookOperations.createAddressBook();
				performOperations(fileOperated);
				break;
			case 2:
				fileOperated = AddressBookOperations.displayAddressBook();
				if (fileOperated == null)
					System.out.println("No such address book present.");
				else
					performOperations(fileOperated);
				break;
			case 3:
				int sizeCityDict = 0, totalSizeCityDict = 0;
				System.out.println("Enter city name to list persons : ");
				input = sc.next();

				System.out.println("Person lives in city " + input + " are :");

				for (File filename : files) {
					sizeCityDict = searchFromCity(filename.getName(), input);
					totalSizeCityDict = totalSizeCityDict + sizeCityDict;
				}
				if (totalSizeCityDict == 0)
					System.out.println("No one.");
				System.out.println("Total count of persons in city " + input + " is : " + totalSizeCityDict);
				break;
			case 4:
				int sizeStateDict = 0, totalSizeStateDict = 0;
				System.out.println("Enter state name to list persons : ");
				input = sc.next();
				System.out.println("Person lives in state " + input + " are :");

				for (File filename : files) {
					sizeStateDict = searchFromState(filename.getName(), input);
					totalSizeStateDict = totalSizeStateDict + sizeStateDict;
				}
				if (totalSizeStateDict == 0)
					System.out.println("No one.");
				System.out.println("Total count of persons in city " + input + " is : " + totalSizeStateDict);
				break;
			case 5:
				System.out.println("You exit the program.");
				System.exit(0);
			default:
				System.out.println("Wrong choice");
			}
		} while (option != 5);

	}

	public static void performOperations(String workOnAddressBook) {
		AddressBookOperations addressbookoperations = new AddressBookOperations(workOnAddressBook);
		String str, str1;
		int choice;

		do {
			System.out.print(
					" 1.Add a person \n 2.Update person info \n 3.Delete person \n 4.Sort by name \n 5.Sort by city \n 6.Sort by state \n 7.Sort by zip \n 8.Print all contacts \n 9.Close the address book. \n Enter your choice : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter first and last name to add details : ");
				str = sc.next();
				str1 = sc.next();
				addressbookoperations.addPerson(str, str1);
				break;
			case 2:
				System.out.println("Enter first and last name to update details : ");
				str = sc.next();
				str1 = sc.next();
				addressbookoperations.updatePerson(str, str1);
				break;
			case 3:
				System.out.print("Enter first and last name to delete a person :");
				str = sc.next();
				str1 = sc.next();
				addressbookoperations.removePerson(str, str1);
				System.out.println("Person named " + str + " " + str1 + " deleted from address book.");
				break;
			case 4:
				System.out.println("Address book is sorted by name.");
				addressbookoperations.sortByName();
				break;
			case 5:
				System.out.println("Address book is sorted by city.");
				addressbookoperations.sortByCity();
				break;
			case 6:
				System.out.println("Address book is sorted by state.");
				addressbookoperations.sortByState();
				break;
			case 7:
				System.out.println("Address book is sorted by zip codes.");
				addressbookoperations.sortByZip();
				break;
			case 8:
				addressbookoperations.printAll();
				break;
			case 9:
				System.out.println(
						"You closed the '" + workOnAddressBook.replaceFirst("[.][^.]+$", "") + " ' address book.");
				addressbookoperations.setFile(workOnAddressBook);
				break;
			default:
				System.out.println("Wrong choice.");
			}
		} while (choice != 9);
	}

	public static int searchFromCity(String filename, String city) {
		AddressBookOperations addressbookoperation = new AddressBookOperations(filename, city);
		addressbookoperation.searchCity(filename, city);
		return addressbookoperation.cityPerson.size();
	}

	public static int searchFromState(String filename, String state) {
		AddressBookOperations addressbookoperation = new AddressBookOperations(filename, state);
		addressbookoperation.searchState(filename, state);
		return addressbookoperation.statePerson.size();
	}

}
