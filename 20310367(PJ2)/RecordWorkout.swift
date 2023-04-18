//
//  RecordWorkout.swift
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

class RecordWorkout: UIViewController {
    
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    
    @IBOutlet weak var timerLabel: UILabel!
    @IBOutlet weak var btnStart: UIButton!
    @IBOutlet weak var btnStop: UIButton!
    @IBOutlet weak var btnPause: UIButton!
    @IBOutlet weak var addSeconds: UIButton!
    @IBOutlet weak var minusSeconds: UIButton!
    
    @IBOutlet weak var exercise: UITextField!
    @IBOutlet weak var reps: UITextField!
    @IBOutlet weak var sets: UITextField!
    @IBOutlet weak var weight: UITextField!
    
    
    let datePicker = UIDatePicker()
    
    //showing date
    @IBOutlet weak var textField: UITextField!
    
    var timer:Timer = Timer()
    var seconds = 60
    
    override func viewDidLoad() {
        super.viewDidLoad()
        btnStart.setTitleColor(UIColor.green, for: .normal)
        btnStop.setTitleColor(UIColor.red, for: .normal)
        
        createDatePicker()
    }
    
    func createToolbar() -> UIToolbar{
        // toolbar
        let toolbar = UIToolbar()
        toolbar.sizeToFit()
        
        //done button
        let doneBtn = UIBarButtonItem(barButtonSystemItem: .done, target: nil, action: #selector(donePressed))
        toolbar.setItems([doneBtn], animated: true)
        
        return toolbar
    }
    
    func createDatePicker(){
        datePicker.preferredDatePickerStyle = .wheels
        datePicker.datePickerMode = .date
        
        textField.textAlignment = .center
        textField.inputView = datePicker
        textField.inputAccessoryView = createToolbar()
        
    }
    
    @objc func donePressed(){
        let dateFormatter = DateFormatter()
        dateFormatter.dateStyle = .medium
        dateFormatter.timeStyle = .none
        
        self.textField.text = dateFormatter.string(from: datePicker.date)
        self.view.endEditing(true)
    }
    
    @IBAction func start(_ sender: Any) {
        timer.invalidate()
        
        timer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(RecordWorkout.timerClass), userInfo: nil, repeats: true)
    }
    @IBAction func pause(_ sender: Any) {
        timer.invalidate()
    }
    
    @IBAction func reset(_ sender: Any) {
        timer.invalidate()
        seconds = 60
        timerLabel.text = String(seconds)
        
    }
    @IBAction func add(_ sender: Any) {
        seconds = seconds + 5
        timerLabel.text = String(seconds)
    }
    
    @IBAction func minus(_ sender: Any) {
        seconds = seconds - 5
        timerLabel.text = String(seconds)
    }
    
    @objc func timerClass(){
        seconds -= 1
        timerLabel.text = String(seconds)
        
        if(seconds == 0){
            timer.invalidate()
        }
    }
    
    @IBAction func save(_ sender: Any) {
        let model = Record(context: context)
        model.timeStamp = self.textField.text
        model.exercise = exercise.text
        model.reps = reps.text
        model.sets = sets.text
        model.weight = weight.text
        
        saveContext()
        let alert = UIAlertController(title: "Message", message: "Recorded Your Result Successfully :)", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Okay", style: .default, handler: nil))
        self.present(alert, animated: true)
        
        print("Data Added")
    }
    
}
