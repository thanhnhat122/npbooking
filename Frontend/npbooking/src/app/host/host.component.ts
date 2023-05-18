import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { DataState } from '../enum/data.state.enum';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { HotelService } from '../service/hotel.service';
import { ProvinceService } from '../service/province.service';
import { RoomService } from '../service/room.service';
import { ImageService } from '../service/image.service';
import { UserAuthService } from '../service/user-auth.service';
import { UserService } from '../service/user.service';
import { ConfirmationService, MenuItem, TreeNode } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { Room } from '../model/room';
import { BookingService } from '../service/booking.service';
import { Booking } from '../enum/booking.enum';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { Hotel } from '../model/hotel';
import { Province } from '../model/province';
import { benefitCombobox } from '../model/combobox';
@Component({
  selector: 'app-host',
  templateUrl: './host.component.html',
  styleUrls: ['./host.component.css'],
  providers: [MessageService, ConfirmationService],
})
export class HostComponent implements OnInit {
  hotelState$!: Observable<AppState<CustomResponse>>;
  treeLeft: TreeNode[] = [
    {
      key: '0',
      label: 'Khách sạn',
      type: 'rootHotel',
      icon: 'fa-solid fa-hotel',
      children: [],
    },
  ];
  selectedNodeTreeLeft: TreeNode = {};
  contextMenuTreeLeft: MenuItem[] = [];
  hotelForm = new FormGroup({
    name: new FormControl<string | null>(null, [Validators.required]),
    description: new FormControl('', [Validators.required]),
    province: new FormControl<number | null>(null, [Validators.required]),
    address: new FormControl('', [Validators.required]),
    benefit: new FormControl<string[] | null>(null, [Validators.required]),
    star: new FormControl(0, [
      Validators.required,
      Validators.min(0),
      Validators.max(5),
    ]),
    breakfast: new FormControl(true, [Validators.required]),
    distanceCenter: new FormControl(0, [Validators.required]),
    sustainTour: new FormControl(true, [Validators.required]),
    sea: new FormControl(true, [Validators.required]),
  });
  showHotelForm: boolean = false;
  provinces: Province[] = [];
  benefits: object[] = benefitCombobox;
  loadingHotel: boolean = false;
  disabledSave: boolean = true;
  disabledDelete: boolean = true;

  action: string = 'add';
  readonly DataState = DataState;
  readonly Booking = Booking;

  userState$!: Observable<AppState<CustomResponse>>;
  roomState$!: Observable<AppState<CustomResponse>>;
  bookingState$!: Observable<AppState<CustomResponse>>;
  imgRoomState$!: Observable<AppState<CustomResponse>>;
  isLogged: boolean;

  roomForm = new FormGroup({
    type: new FormControl<string | null>(null, [Validators.required]),
    price: new FormControl<number | null>(null, [
      Validators.required,
      Validators.min(0),
    ]),
    discount: new FormControl(0, [Validators.required, Validators.min(0)]),
    size: new FormControl('', [Validators.required]),
    peopleNumber: new FormControl(0, [Validators.required, Validators.min(0)]),
    singleBed: new FormControl(0, [Validators.required, Validators.min(0)]),
    doubleBed: new FormControl(0, [Validators.required, Validators.min(0)]),
    quantity: new FormControl(0, [Validators.required, Validators.min(0)]),
    benefitRoom: new FormControl([], [Validators.required]),
    view: new FormControl([], [Validators.required]),
    bathRoom: new FormControl([], [Validators.required]),
    convenient: new FormControl([], [Validators.required]),
    smoking: new FormControl(true, [Validators.required]),
  });
  rooms: [] = [];
  hotelId: string = '';
  roomId: string = '';
  activeIndex: number = 0;
  displayEditRoom: boolean = false;
  displayManageImgRoom: boolean = false;
  constructor(
    private userService: UserService,
    private hotelService: HotelService,
    private userAuthService: UserAuthService,
    private route: Router,
    private provinceService: ProvinceService,
    private roomService: RoomService,
    private messageService: MessageService,
    private bookingService: BookingService,
    private imageService: ImageService,
    private confirmationService: ConfirmationService
  ) {
    this.isLogged = this.userAuthService.isLogged();
    this.hotelForm.valueChanges.subscribe((data) => {
      this.disabledSave = false;
    });
  }
  ngOnInit(): void {
    this.userState$ = this.userService
      .getByEmail$(this.userAuthService.getItem('email') as string)
      .pipe(
        map((response) => {
          if (!response.data) {
            this.isLogged = false;
            this.userAuthService.clearAllItems();
            this.route.navigate(['/login']);
          }
          this.isLogged = true;
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
    this.hotelState$ = this.hotelService
      .getAllHotelByOwner$(this.userAuthService.getItem('email') as string)
      .pipe(
        map((response) => {
          const hotels = response?.data;
          for (const [i, value] of hotels.entries()) {
            const hotel: Hotel = {
              id: value.id,
              name: value.name,
              description: value.description,
              province: value.province,
              address: value.address,
              benefit: value.benefit,
              star: value.star,
              breakfast: value.breakfast,
              distanceCenter: value.distanceCenter,
              sustainTour: value.sustainTour,
              sea: value.sea,
            };
            const node: TreeNode = {
              key: `0-${i}`,
              label: value.name,
              data: hotel,
              type: 'hotel',
              icon: 'fa-solid fa-hotel',
            };
            this.treeLeft.at(0)?.children?.push(node);
          }

          // this.hotelId = response?.data[0].id;
          // console.log(this.hotelId);
          // this.hotelDetailForm.get('name')?.setValue(response?.data[0].name);
          // this.hotelDetailForm
          //   .get('description')
          //   ?.setValue(response?.data[0].description);
          // this.hotelDetailForm
          //   .get('province')
          //   ?.setValue(response?.data[0].province);
          // this.hotelDetailForm
          //   .get('address')
          //   ?.setValue(response?.data[0].address);
          // this.hotelDetailForm
          //   .get('benefit')
          //   ?.setValue(response?.data[0].benefit?.split(','));
          // this.hotelDetailForm.get('star')?.setValue(response?.data[0].star);
          // this.hotelDetailForm
          //   .get('breakfast')
          //   ?.setValue(response?.data[0].breakfast);
          // this.hotelDetailForm
          //   .get('distanceCenter')
          //   ?.setValue(response?.data[0].distanceCenter);
          // this.hotelDetailForm
          //   .get('sustainTour')
          //   ?.setValue(response?.data[0].sustainTour);
          // this.hotelDetailForm.get('sea')?.setValue(response?.data[0].sea);
          // this.roomState$ = this.roomService
          //   .getRoomsByHotelId$(this.hotelId)
          //   .pipe(
          //     map((response) => {
          //       this.rooms = response.data;
          //       return { dataState: DataState.LOADED_STATE, appData: response };
          //     }),
          //     startWith({ dataState: DataState.LOADING_STATE }),
          //     catchError((error: string) => {
          //       return of({ dataState: DataState.ERROR_STATE, error });
          //     })
          //   );
          // this.bookingState$ = this.bookingService
          //   .getByHotelId$(this.hotelId)
          //   .pipe(
          //     map((response) => {
          //       this.rooms = response.data;
          //       return { dataState: DataState.LOADED_STATE, appData: response };
          //     }),
          //     startWith({ dataState: DataState.LOADING_STATE }),
          //     catchError((error: string) => {
          //       return of({ dataState: DataState.ERROR_STATE, error });
          //     })
          //   );
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
    this.provinceService.getAll$.subscribe((data) => {
      this.provinces = data.data;
    });
  }

  onNodeContextMenuSelectTreeLeft(event: any): void {
    const typeNode = event.node.type;
    switch (typeNode) {
      case 'rootHotel':
        this.contextMenuTreeLeft = [
          {
            label: 'Thêm khách sạn',
            icon: 'fa-solid fa-plus',
            command: (event) => (this.showHotelForm = true),
          },
        ];
        break;

      default:
        break;
    }
  }

  clearContextMenuTreeLeft(): void {
    this.contextMenuTreeLeft.length = 0;
  }

  onNodeSelectTreeLeft(event: any) {
    const typeNode = event.node.type;
    switch (typeNode) {
      case 'hotel':
        this.loadHotelInfo(this.selectedNodeTreeLeft.data);
        break;
    }
  }
  loadHotelInfo(hotel: Hotel) {
    this.hotelForm.get('name')?.setValue(hotel.name);
    this.hotelForm.get('description')?.setValue(hotel.description);
    this.hotelForm.get('province')?.setValue(hotel.province);
    this.hotelForm.get('address')?.setValue(hotel.address);
    this.hotelForm.get('benefit')?.setValue(hotel.benefit?.split(','));
    this.hotelForm.get('star')?.setValue(hotel.star);
    this.hotelForm.get('breakfast')?.setValue(hotel.breakfast);
    this.hotelForm.get('distanceCenter')?.setValue(hotel.distanceCenter);
    this.hotelForm.get('sustainTour')?.setValue(hotel.sustainTour);
    this.hotelForm.get('sea')?.setValue(hotel.sea);
    this.disabledSave = true;
  }

  // editHotelDetail(): void {
  //   const { benefit, ...rest } = this.hotelDetailForm.value;
  //   const temp = benefit.join(',');
  //   const payload = { ...rest, benefit: temp };
  //   this.hotelService
  //     .update$(this.hotelId, payload)
  //     .subscribe((data) => console.log(data));
  // }
  // loadHotelDetail(id: string) {
  //   this.hotelId = id;
  //   this.hotelService.getById$(id).subscribe((hotel) => {
  //     this.hotelDetailForm.get('name')?.setValue(hotel?.data.name);
  //     this.hotelDetailForm
  //       .get('description')
  //       ?.setValue(hotel?.data.description);
  //     this.hotelDetailForm.get('province')?.setValue(hotel?.data.province);
  //     this.hotelDetailForm.get('address')?.setValue(hotel?.data.address);
  //     this.hotelDetailForm
  //       .get('benefit')
  //       ?.setValue(hotel?.data.benefit?.split(','));
  //     this.hotelDetailForm.get('star')?.setValue(hotel?.data.star);
  //     this.hotelDetailForm.get('breakfast')?.setValue(hotel?.data.breakfast);
  //     this.hotelDetailForm
  //       .get('distanceCenter')
  //       ?.setValue(hotel?.data.distanceCenter);
  //     this.hotelDetailForm
  //       .get('sustainTour')
  //       ?.setValue(hotel?.data.sustainTour);
  //     this.hotelDetailForm.get('sea')?.setValue(hotel?.data.sea);
  //   });
  //   this.roomState$ = this.roomService.getRoomsByHotelId$(this.hotelId).pipe(
  //     map((response) => {
  //       this.rooms = response.data;
  //       return { dataState: DataState.LOADED_STATE, appData: response };
  //     }),
  //     startWith({ dataState: DataState.LOADING_STATE }),
  //     catchError((error: string) => {
  //       return of({ dataState: DataState.ERROR_STATE, error });
  //     })
  //   );
  //   this.bookingState$ = this.bookingService.getByHotelId$(this.hotelId).pipe(
  //     map((response) => {
  //       this.rooms = response.data;
  //       return { dataState: DataState.LOADED_STATE, appData: response };
  //     }),
  //     startWith({ dataState: DataState.LOADING_STATE }),
  //     catchError((error: string) => {
  //       return of({ dataState: DataState.ERROR_STATE, error });
  //     })
  //   );
  // }
  async addHotel() {
    const { benefit, ...rest } = this.hotelForm.value;
    const payload = {
      ...rest,
      benefit: benefit?.join(','),
      owner: this.userAuthService.getItem('email'),
    };
    const a = await this.hotelService.insert$(payload).toPromise();
    this.showHotelForm = false;
    this.hotelState$ = this.hotelService
      .getAllHotelByOwner$(this.userAuthService.getItem('email') as string)
      .pipe(
        map((response) => {
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
  }
  async deleteHotel() {
    const a = await this.hotelService.delete$(this.hotelId).toPromise();
    this.hotelState$ = this.hotelService
      .getAllHotelByOwner$(this.userAuthService.getItem('email') as string)
      .pipe(
        map((response) => {
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
  }
  showEditRoom(id: string) {
    this.action = 'edit';
    this.displayEditRoom = true;
    this.roomId = id;
    this.roomService.getById$(id).subscribe((room) => {
      this.roomForm.get('type')?.setValue(room?.data.type);
      this.roomForm.get('price')?.setValue(room?.data.price);
      this.roomForm.get('discount')?.setValue(room?.data.discount);
      this.roomForm.get('size')?.setValue(room?.data.size);
      this.roomForm.get('peopleNumber')?.setValue(room?.data.peopleNumber);
      this.roomForm.get('singleBed')?.setValue(room?.data.singleBed);
      this.roomForm.get('doubleBed')?.setValue(room?.data.doubleBed);
      this.roomForm.get('quantity')?.setValue(room?.data.quantity);
      this.roomForm
        .get('benefitRoom')
        ?.setValue(room?.data.benefitRoom?.split(','));
      this.roomForm.get('bathRoom')?.setValue(room?.data.bathRoom?.split(','));
      this.roomForm.get('view')?.setValue(room?.data.view?.split(','));
      this.roomForm
        .get('convenient')
        ?.setValue(room?.data.convenient?.split(','));
      this.roomForm.get('smoking')?.setValue(room?.data.smoking);
    });
  }
  editRoom() {
    const { quantity, benefitRoom, bathRoom, view, convenient, ...rest } =
      this.roomForm.value;
    const payload = {
      ...this.roomForm.value,
      remainRoom: quantity,
      benefitRoom: benefitRoom?.join(','),
      bathRoom: bathRoom?.join(','),
      view: view?.join(','),
      convenient: convenient?.join(','),
      hotelId: this.hotelId,
    };
    console.log(this.roomForm.value);
    if (this.action === 'edit') {
      this.roomService.update$(this.roomId, payload).subscribe((data) => {
        console.log(data);
        this.roomState$ = this.roomService
          .getRoomsByHotelId$(this.hotelId)
          .pipe(
            map((response) => {
              this.rooms = response.data;
              return { dataState: DataState.LOADED_STATE, appData: response };
            }),
            startWith({ dataState: DataState.LOADING_STATE }),
            catchError((error: string) => {
              return of({ dataState: DataState.ERROR_STATE, error });
            })
          );
        this.displayEditRoom = false;
        this.messageService.add({
          severity: 'success',
          summary: 'Cập nhật thành công',
          detail: `Cập nhật loại phòng thành công`,
        });
      });
    } else {
      this.roomService.insert$(payload).subscribe((data) => {
        console.log(data);
        this.roomState$ = this.roomService
          .getRoomsByHotelId$(this.hotelId)
          .pipe(
            map((response) => {
              this.rooms = response.data;
              return { dataState: DataState.LOADED_STATE, appData: response };
            }),
            startWith({ dataState: DataState.LOADING_STATE }),
            catchError((error: string) => {
              return of({ dataState: DataState.ERROR_STATE, error });
            })
          );
        this.displayEditRoom = false;
        this.messageService.add({
          severity: 'success',
          summary: 'Thêm thành công',
          detail: `Thêm loại phòng thành công`,
        });
      });
    }
  }
  showAddRoom() {
    this.action = 'add';
    this.displayEditRoom = true;
    this.roomForm.reset();
    console.log(this.hotelId);
  }
  deleteRoom(id: string) {
    this.roomService.delete$(id).subscribe((data) => {
      console.log(data);
      this.messageService.add({
        severity: 'success',
        summary: 'Xoá thành công',
        detail: `Xoá loại phòng thành công`,
      });
      this.roomState$ = this.roomService.getRoomsByHotelId$(this.hotelId).pipe(
        map((response) => {
          this.rooms = response.data;
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
    });
  }
  updateBookingStatus(id: string) {
    this.bookingService.getById$(id).subscribe((booking) => {
      booking.data.status = 'Đã xác nhận';
      console.log('before');
      this.bookingService.update$(id, booking.data).subscribe((data) => {
        console.log('after');
        this.bookingState$ = this.bookingService
          .getByHotelId$(this.hotelId)
          .pipe(
            map((response) => {
              this.rooms = response.data;
              return { dataState: DataState.LOADED_STATE, appData: response };
            }),
            startWith({ dataState: DataState.LOADING_STATE }),
            catchError((error: string) => {
              return of({ dataState: DataState.ERROR_STATE, error });
            })
          );
        this.messageService.add({
          severity: 'success',
          summary: 'Cập nhật thành công',
          detail: `Cập nhật trạng thái thành công`,
        });
      });
    });
  }
  logOut(): void {
    this.isLogged = false;
    this.userAuthService.clearAllItems();
    this.route.navigate(['/']);
  }
  manageImg(roomId: string): void {
    this.displayManageImgRoom = true;
    this.roomId = roomId;
    this.imgRoomState$ = this.imageService
      .getAllImgRoom$(this.hotelId, roomId)
      .pipe(
        map((response) => {
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
  }
  deleteImage(event: Event, imageId: string): void {
    this.confirmationService.confirm({
      target: event.target!,
      message: 'Bạn chắc chắn muốn xoá chứ?',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.imageService.delete$(imageId).subscribe((data) => {
          this.messageService.add({
            severity: 'info',
            summary: 'Xoá thành công',
            detail: 'Bạn đã xoá ảnh thành công',
          });
          this.imgRoomState$ = this.imageService
            .getAllImgRoom$(this.hotelId, this.roomId)
            .pipe(
              map((response) => {
                return { dataState: DataState.LOADED_STATE, appData: response };
              }),
              startWith({ dataState: DataState.LOADING_STATE }),
              catchError((error: string) => {
                return of({ dataState: DataState.ERROR_STATE, error });
              })
            );
        });
      },
      reject: () => {
        // this.messageService.add({severity:'error', summary:'Rejected', detail:'You have rejected'});
      },
    });
  }
  async onUpload(e: any, form: any) {
    for (let file of e.files) {
      await this.imageService.upload(this.hotelId, this.roomId, file).subscribe(
        (data) => {
          if (data.errCode === '200') {
            this.messageService.add({
              severity: 'info',
              summary: 'Thêm thành công',
              detail: 'Bạn đã thêm ảnh thành công',
            });
            this.imgRoomState$ = this.imageService
              .getAllImgRoom$(this.hotelId, this.roomId)
              .pipe(
                map((response) => {
                  return {
                    dataState: DataState.LOADED_STATE,
                    appData: response,
                  };
                }),
                startWith({ dataState: DataState.LOADING_STATE }),
                catchError((error: string) => {
                  return of({ dataState: DataState.ERROR_STATE, error });
                })
              );
          } else {
            this.messageService.add({
              severity: 'error',
              summary: 'Lỗi upload',
              detail: 'Có lỗi xảy ra, vui lòng thử lại',
            });
          }
        },
        (error) => {}
      );
    }

    form.clear();
  }
}
