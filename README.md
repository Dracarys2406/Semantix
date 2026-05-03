
## App flow

* View (Compose) emits an Intent (e.g., SearchIntent.OnSearchClicked).

* ViewModel receives the Intent and calls the UseCase.

* UseCase fetches data through the Repository.

* ViewModel receives the result and updates the State (e.g., SearchState.Success).

* View observes the new State and recomposes.