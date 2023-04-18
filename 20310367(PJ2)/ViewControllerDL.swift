//
//  ViewControllerDL.swift
//  iWorkout
//
//  Created by Pataratanan Visitserngtrakul on 27/1/22.
//  Copyright © 2022 Pataratanan Visitserngtrakul. All rights reserved.
//
/*
 
 Pataratanan Visitserngtrakul 20310367

 DECLARATION
 I hereby certify that no other part of this submission has been copied from any other sources, including the Internet, books or other student’s work except the ones I have listed below. No part of the code has been written/produced for me by another person or copied from any other source.
 I hold a copy of this assignment that I can produce if the original is lost or damaged.
 
 */

import UIKit
import CoreData
import MessageUI
class ViewControllerDL: UIViewController,UITableViewDelegate,UITableViewDataSource, MFMessageComposeViewControllerDelegate,UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    var imagePicker: UIImagePickerController!
    func messageComposeViewController(_ controller: MFMessageComposeViewController, didFinishWith result: MessageComposeResult) {
        switch result.rawValue {
        case MessageComposeResult.cancelled.rawValue:
        print("Message was cancelled")
        controller.dismiss(animated: true, completion: nil) case MessageComposeResult.failed.rawValue:
        print("Message failed")
        controller.dismiss(animated: true, completion: nil) case MessageComposeResult.sent.rawValue:
        print("Message was sent")
        controller.dismiss(animated: false, completion: nil)
        default:
        break
        controller.dismiss(animated: true, completion: nil)
            
        }
    }
    
    

    @IBOutlet weak var smsBtn: UIBarButtonItem!
    @IBOutlet weak var table: UITableView!
    override func viewDidLoad() {
           super.viewDidLoad()
           
           table.delegate = self
           table.dataSource = self
       }
    
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    
    var result:[Record] = []
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return result.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = table.dequeueReusableCell(withIdentifier: "id3", for: indexPath) as! pCell2
        
        let rslt = result[indexPath.row]
        cell.date.text = rslt.timeStamp
        cell.exercise.text = rslt.exercise
        cell.reps.text = rslt.reps
        cell.sets.text = rslt.sets
        cell.weight.text = rslt.weight
        
        return cell
    }
    
    
   
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        fetch()
        table.reloadData()
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 120
    }
    
    func fetch(){
        
        let request = NSFetchRequest<Record>(entityName: "Record")
        
        do{
            result = try context.fetch(request)
        }catch{
            print(error)
        }
    }
    func tableView(_ tableView: UITableView, leadingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
           let delete = UIContextualAction(style: .destructive, title: "") { _,_,_ in
               
               self.context.delete(self.result[indexPath.row])
               saveContext()
               self.fetch()
               
               tableView.deleteRows(at: [indexPath], with: .automatic)
           }
           
           let swipe = UISwipeActionsConfiguration(actions: [delete])
           
           return swipe
       }
    
    @IBAction func smsBtn(_ sender: Any) {
        if MFMessageComposeViewController.canSendText() {
        let composeVC = MFMessageComposeViewController()
        composeVC.messageComposeDelegate = self
         
        // Configure the fields of the interface.
        composeVC.recipients = ["0413036929"]
        composeVC.body = "Hello World :)"
        composeVC.messageComposeDelegate = self;
        // Present the view controller modally.
        self.present(composeVC, animated: true, completion: nil)
        }else{
            print("Error with sending SMS")
        }
    }
    
    /*
    @IBAction func takePhoto(_ sender: Any) {
        imagePicker = UIImagePickerController()
        imagePicker.delegate = self
        imagePicker.sourceType = .camera
        //crash if no camera
           present(imagePicker, animated: true,
        completion: nil)
    }
    */
    
}
