//
//  SetupExercise.swift
//  iWorkout
//
//  Created by Pataratanan Visitserngtrakul on 22/1/22.
//  Copyright © 2022 Pataratanan Visitserngtrakul. All rights reserved.
//
/*

Pataratanan Visitserngtrakul 20310367

DECLARATION
I hereby certify that no other part of this submission has been copied from any other sources, including the Internet, books or other student’s work except the ones I have listed below. No part of the code has been written/produced for me by another person or copied from any other source.
I hold a copy of this assignment that I can produce if the original is lost or damaged.

*/
import UIKit

class SetupExercise: UIViewController {
    
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext

    @IBOutlet weak var categories: UITextField!
    @IBOutlet weak var exercises: UITextField!
    @IBOutlet weak var repetitions: UITextField!
    @IBOutlet weak var sets: UITextField!
    @IBOutlet weak var weight: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

    }

    //Button to save data function
    @IBAction func save(_ sender: Any) {
        let model = Workout(context: context)
        model.categories = categories.text
        model.exercises = exercises.text
        model.repetitions = repetitions.text
        model.sets = sets.text
        model.weight = weight.text
        saveContext()
        
        //db.addRow(categories: categories.text!, exercises: exercises.text!, repetitions: repetitions.text!, sets: sets.text!, weight: weight.text!)

        
        let alert = UIAlertController(title: "Message", message: "Successfully Added Data :)", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Okay", style: .default, handler: nil))
        self.present(alert, animated: true)
    }
    
}
