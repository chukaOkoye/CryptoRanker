# CryptoRanker Android App

FUNCTIONALITY

:heavy_check_mark: 1. When	the	app	is	started,	load	and	display	a	list	of	coins
   
   :heavy_check_mark: i. Order	entries	by	rank


:heavy_check_mark: 4. Display	a	divider	between	each	entry

:heavy_check_mark: 5. Display	any	extra	coin	info in	some	kind	of	popup/fragment	when coin entry	is	clicked	based	on	the	second	API	request,	with	said	id as	the	query.

:heavy_check_mark: 6. Provide	some	kind	of	refresh	option	that	reloads	the	list

:heavy_check_mark: 7. Display	an	error	message	if	the	list	cannot	be	loaded	(e.g.	no	network)

:heavy_check_mark: 8. (Extra	credit)	Animate	list	items	(hint	–	Jetpack	Compose	allows	this	easily).

DELIVERABLES

:heavy_check_mark: 1. Write	the	app	using	Kotlin	only.

:heavy_check_mark: 2. Use	Jetpack	Compose	for	UI.

:heavy_check_mark: 3. Use	MVVM	design	pattern,	combined	with	RxJava	or	Coroutines	(remember;	keep	it simple!)

4. Write	unit	tests	against	the	ViewModel	(and	anywhere	you	feel	necessary,	using	i.e.
   Mockito),	so	it’s	tested	that	i.e.	entries	are	ordered	by	name,	etc.

## My Approach
- Worked on making sure the main objectives were completed, made sure each part of code adhered by MVVM standards and created in Jetpack Compose.
- The main screen is a list that loads on first composition, but retains status unless the 'Refresh' button is clicked, which reruns the ViewModel call to load all coins.
- Each button directs to another composable with additional information about the cryptocurrency chosen, by passing the coinID to Retrofit/Coroutines and seamlessly pulls real-time cryptocurrency data from the CoinPaprika API.
- The ViewModelTest is incomplete due to issues with mocking the state flow, unable to mimic the CoinListState to be success when ran in the test environment.
- Future work: I would work on making the ViewModelTest work to test entries, and add a network cache for offline storage.
