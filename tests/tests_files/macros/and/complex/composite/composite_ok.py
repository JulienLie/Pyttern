class Composant:
    def executer(self):
        raise NotImplementedError("This method should be implemented by subclasses.")
    
class Composite(Composant):
    def __init__(self):
        self.composants = []

    def ajouter(self, composant):
        self.composants.append(composant)

    def supprimer(self, composant):
        self.composants.remove(composant)

    def executer(self):
        for composant in self.composants:
            composant.executer()

class Feuille(Composant):
    def __init__(self, nom):
        self.nom = nom

    def executer(self):
        print(f"Exécution de la feuille: {self.nom}")