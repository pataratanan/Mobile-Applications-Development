//
//  SaveDaily.swift
//  iWorkout
//
//  Created by Pataratanan Visitserngtrakul on 24/1/22.
//  Copyright © 2022 Pataratanan Visitserngtrakul. All rights reserved.
//
/*

Pataratanan Visitserngtrakul 20310367

DECLARATION
I hereby certify that no other part of this submission has been copied from any other sources, including the Internet, books or other student’s work except the ones I have listed below. No part of the code has been written/produced for me by another person or copied from any other source.
I hold a copy of this assignment that I can produce if the original is lost or damaged.

*/
import UIKit

class SaveDaily: UIViewController,UIPickerViewDelegate,UIPickerViewDataSource {
    
    @IBOutlet weak var picker: UIPickerView!
    @IBOutlet weak var exercise: UITextField!
    @IBOutlet weak var day: UITextField!
    
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    
    var ref = 0
    let dayArray = ["Squats","Leg Press","DeadLift","Lats","Back Extension","Seated Calf Raise","Leg Curl","Push Ups","Planks","Crunches","Lunges","Shadow Boxing"]
    let colorArray = [UIColor.yellow,#colorLiteral(red: 0.9098039269, green: 0.4784313738, blue: 0.6431372762, alpha: 1),#colorLiteral(red: 0, green: 0.6245825291, blue: 0, alpha: 1),#colorLiteral(red: 0.9372549057, green: 0.3490196168, blue: 0.1921568662, alpha: 1),#colorLiteral(red: 0.2392156869, green: 0.6745098233, blue: 0.9686274529, alpha: 1),#colorLiteral(red: 0.5568627715, green: 0.3529411852, blue: 0.9686274529, alpha: 1),#colorLiteral(red: 1, green: 0, blue: 0.1036087945, alpha: 1),#colorLiteral(red: 0.1019607857, green: 0.2784313858, blue: 0.400000006, alpha: 1),#colorLiteral(red: 0.9607843161, green: 0.7058823705, blue: 0.200000003, alpha: 1),#colorLiteral(red: 0.501960814, green: 0.501960814, blue: 0.501960814, alpha: 1),#colorLiteral(red: 0.1764705926, green: 0.01176470611, blue: 0.5607843399, alpha: 1),#colorLiteral(red: 0.521568656, green: 0.1098039225, blue: 0.05098039284, alpha: 1)]
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        dayArray.count
    }
    
    //show all data to PickerView
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        day.text = dayArray[row]
        
        ref = row
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        picker.delegate = self
        picker.dataSource = self
        
        // Do any additional setup after loading the view.
    }
    
    func pickerView(_ pickerView: UIPickerView, attributedTitleForRow row: Int, forComponent component: Int) -> NSAttributedString? {
        
        let attrb = NSAttributedString(string: dayArray[row], attributes: [NSAttributedString.Key.foregroundColor : colorArray[row]])
        
        return attrb
    }
    
    @IBAction func save(_ sender: Any) {
        let model = MyList(context: context)
        model.program = exercise.text
        model.day = day.text
        model.color = colorArray[ref]
        
        saveContext()
        
        let alert = UIAlertController(title: "Message", message: "Successfully Added Data :)", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Okay", style: .default, handler: nil))
        self.present(alert, animated: true)
        
    }
    
}
