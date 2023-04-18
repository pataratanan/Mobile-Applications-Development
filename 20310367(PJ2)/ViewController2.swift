//
//  ViewController2.swift
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
import CoreData

class ViewController2: UIViewController,UITableViewDataSource,UITableViewDelegate {
    
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    
    //declare variable to hold entity from db as empty first
    var result:[MyList] = []
    
    @IBOutlet weak var table: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()

        table.delegate = self
        table.dataSource = self
        // Do any additional setup after loading the view.
        
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        fetch()
        table.reloadData()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return result.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = table.dequeueReusableCell(withIdentifier: "id", for: indexPath) as! SetupDaily
        
        //show data as we set Cell in Main.storyboard
        let rslt = result[indexPath.row]
        cell.program.text = rslt.program
        cell.day.text = rslt.day
        cell.day.textColor = rslt.color
        
        return cell
    }
    
    //function to fetch data from db
    func fetch(){
        let request = NSFetchRequest<MyList>(entityName: "MyList")
        
        do{
           result = try context.fetch(request)
        }catch{
            print(error)
        }
    }

    //function to set Hight of each Cell
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 95
    }

    //function to delete each Cell
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
    

}
