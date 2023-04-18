//
//  ViewControllerEx.swift
//  iWorkout
//
//  Created by Pataratanan Visitserngtrakul on 25/1/22.
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
class ViewControllerEx: UIViewController,UITableViewDataSource,UITableViewDelegate {
    let context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    
    var result:[Workout] = []
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return result.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = table.dequeueReusableCell(withIdentifier: "id2", for: indexPath) as! pCell
        
        let rslt = result[indexPath.row]
        cell.exercise.text = rslt.exercises
        cell.sets.text = rslt.sets
        cell.repetitions.text = rslt.repetitions
        cell.weight.text = rslt.weight
        
        return cell
    }

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
    
    func fetch(){
        let request = NSFetchRequest<Workout>(entityName: "Workout")
        
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

}
