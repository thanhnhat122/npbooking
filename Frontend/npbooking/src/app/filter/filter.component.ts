import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { FilterDTO } from '../dto/filter.dto';
import { DataState } from '../enum/data.state.enum';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { FilterService } from '../service/filter.service';
import { ProvinceService } from '../service/province.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css'],
})
export class FilterComponent implements OnInit {
  filterState$!: Observable<AppState<CustomResponse>>;
  provinceState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  provinceId: number;
  priceFilter = [
    {
      id: 'priceFilter0',
      name: 'Mặc định',
      min: -1,
      max: -1,
      checked: true,
    },
    {
      id: 'priceFilter1',
      name: 'Dưới VND 2.000.000',
      min: -1,
      max: 2000000,
      checked: false,
    },
    {
      id: 'priceFilter2',
      name: 'VND 2.000.000 - VND 4.000.000',
      min: 2000000,
      max: 4000000,
      checked: false,
    },
    {
      id: 'priceFilter3',
      name: 'VND 4.000.000 - VND 6.000.000',
      min: 4000000,
      max: 6000000,
      checked: false,
    },
    {
      id: 'priceFilter4',
      name: 'VND 6.000.000 - VND 8.000.000',
      min: 6000000,
      max: 8000000,
      checked: false,
    },
    {
      id: 'priceFilter5',
      name: 'Trên VND 8.000.000',
      min: 8000000,
      max: -1,
      checked: false,
    },
  ];
  starFilter = [
    {
      id: 'starFilter5',
      name: '5 sao',
      value: 5,
      checked: false,
    },
    {
      id: 'starFilter4',
      name: '4 sao',
      value: 4,
      checked: false,
    },
    {
      id: 'starFilter3',
      name: '3 sao',
      value: 3,
      checked: false,
    },
    {
      id: 'starFilter2',
      name: '2 sao',
      value: 2,
      checked: false,
    },
    {
      id: 'starFilter1',
      name: '1 sao',
      value: 1,
      checked: false,
    },
    {
      id: 'starFilter0',
      name: 'Chưa xếp hạng',
      value: 0,
      checked: false,
    },
  ];
  scoreFilter = [
    {
      id: 'scoreFilter0',
      name: 'Mặc định',
      value: -1,
      checked: true,
    },
    {
      id: 'scoreFilter1',
      name: 'Trên 9 (Tuyệt hảo)',
      value: 9,
      checked: false,
    },
    {
      id: 'scoreFilter2',
      name: 'Trên 8 (Rất tốt)',
      value: 8,
      checked: false,
    },
    {
      id: 'scoreFilter3',
      name: 'Trên 7 (Tốt)',
      value: 7,
      checked: false,
    },
    {
      id: 'scoreFilter4',
      name: 'Trên 6 (Dễ chịu)',
      value: 6,
      checked: false,
    },
  ];
  distanceCenterFilter = [
    {
      id: 'distanceCenterFilter0',
      name: 'Mặc định',
      value: -1,
      checked: true,
    },
    {
      id: 'distanceCenterFilter1',
      name: 'Dưới 1 km',
      value: 1,
      checked: false,
    },
    {
      id: 'distanceCenterFilter2',
      name: 'Dưới 3 km',
      value: 3,
      checked: false,
    },
    {
      id: 'distanceCenterFilter3',
      name: 'Dưới 5 km',
      value: 5,
      checked: false,
    },
  ];
  numPeople: number = 1;
  rangeDates: Date[];
  payload: FilterDTO;
  cities: {}[] = [];
  checked: boolean = false;
  rangePrices: number[] = [200000, 8000000];
  provinces: any;
  numPlace = 0;
  namePlace = 'Empty';
  constructor(
    private readonly route: ActivatedRoute,
    private filterService: FilterService,
    private provinceService: ProvinceService,
    private router: Router
  ) {
    this.provinceService.getAll$.subscribe((data) => {
      this.provinces = data.data;
    });
    const find = this.router.getCurrentNavigation()?.extras.state;
    this.numPeople = find?.['numPeo'] || 1;
    let tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    const datein =
      find?.['dateIn'] === undefined ? new Date() : new Date(find?.['dateIn']);
    const dateout =
      find?.['dateOut'] === undefined ? tomorrow : new Date(find?.['dateOut']);
    this.rangeDates = [datein, dateout];
    this.provinceId = +this.route.snapshot.paramMap.get('provinceId')! || 1;
    this.payload = {
      province: this.provinceId,
      dateIn: this.getDayDefault().datein,
      dateOut: this.getDayDefault().dateout,
      star: [],
      sustainTour: false,
      sea: false,
      score: -1,
      priceMin: -1,
      priceMax: -1,
      numPeople: 1,
      distanceCenter: -1,
    };
    this.cities = [
      { name: 'Lựa chọn hàng đầu của chúng tôi', code: 'NY' },
      { name: 'Được đánh giá hàng đầu', code: 'RM' },
      { name: 'Hạng sao cao nhất', code: 'LDN' },
      { name: 'Hạng sao thấp nhất', code: 'IST' },
      { name: 'Được đánh giá hàng đầu', code: 'PRS' },
    ];
  }
  private getDayDefault() {
    const date = new Date();
    const datein = [
      date.getFullYear(),
      (date.getMonth() + 1).toString().padStart(2, '0'),
      date.getDate().toString().padStart(2, '0'),
    ].join('-');
    const dateout = [
      date.getFullYear(),
      (date.getMonth() + 1).toString().padStart(2, '0'),
      (date.getDate() + 1).toString().padStart(2, '0'),
    ].join('-');
    return { datein, dateout };
  }

  ngOnInit(): void {
    this.filter(this.payload);
    this.province(this.provinceId as unknown as string);
  }
  private province(id: string) {
    this.provinceState$ = this.provinceService.findById$(id).pipe(
      map((response) => {
        return { dataState: DataState.LOADED_STATE, appData: response };
      }),
      startWith({ dataState: DataState.LOADING_STATE }),
      catchError((error: string) => {
        return of({ dataState: DataState.ERROR_STATE, error });
      })
    );
  }

  filterPrice(id: string): void {
    this.priceFilter.forEach((opt) => {
      if (opt.id === id) {
        opt.checked = true;
      }
      opt.checked = false;
    });
    const temp = this.priceFilter
      .filter((opt) => opt.id === id)
      .map((opt) => ({
        min: opt.min,
        max: opt.max,
      }));
    this.payload.priceMin = temp[0].min;
    this.payload.priceMax = temp[0].max;
    this.filter(this.payload);
  }
  private filter(payload: object) {
    this.filterState$ = this.filterService.filter$(payload).pipe(
      map((response) => {
        this.numPlace = response.data.length;
        this.provinceService
          .findById$(this.provinceId + '')
          .subscribe((data) => {
            this.namePlace = data.data.name;
          });
        return { dataState: DataState.LOADED_STATE, appData: response };
      }),
      startWith({ dataState: DataState.LOADING_STATE }),
      catchError((error: string) => {
        return of({ dataState: DataState.ERROR_STATE, error });
      })
    );
  }

  filterStar(): void {
    const temp = this.starFilter
      .filter((opt) => opt.checked)
      .map((opt) => opt.value);
    this.payload.star = temp;
    this.filter(this.payload);
  }

  filterScore(id: string): void {
    this.scoreFilter.forEach((opt) => {
      if (opt.id === id) {
        opt.checked = true;
      }
      opt.checked = false;
    });
    const temp = this.scoreFilter
      .filter((opt) => opt.id === id)
      .map((opt) => opt.value);
    this.payload.score = temp[0];
    this.filter(this.payload);
  }
  rangePrice(): void {
    this.payload.priceMin = this.rangePrices[0];
    this.payload.priceMax = this.rangePrices[1];
    this.filter(this.payload);
  }
  filterDistanceCenter(id: string): void {
    this.distanceCenterFilter.forEach((opt) => {
      if (opt.id === id) {
        opt.checked = true;
      }
      opt.checked = false;
    });
    const temp = this.distanceCenterFilter
      .filter((opt) => opt.id === id)
      .map((opt) => opt.value);
    this.payload.distanceCenter = temp[0];
    this.filter(this.payload);
  }

  search(): void {
    const date1 = this.rangeDates[0];
    const date2 = this.rangeDates[1];
    const datein = [
      date1.getFullYear(),
      (date1.getMonth() + 1).toString().padStart(2, '0'),
      date1.getDate().toString().padStart(2, '0'),
    ].join('-');
    const dateout = [
      date2.getFullYear(),
      (date2.getMonth() + 1).toString().padStart(2, '0'),
      date2.getDate().toString().padStart(2, '0'),
    ].join('-');
    this.payload.province = this.provinceId;
    this.payload.dateIn = datein;
    this.payload.dateOut = dateout;
    this.payload.numPeople = this.numPeople;
    this.filter(this.payload);
  }
}
